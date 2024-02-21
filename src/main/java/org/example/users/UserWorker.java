package org.example.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserWorker {

    public static final String PREPARE_STATEMENT_INSERT_1 =
            "INSERT INTO users (id, name, birthday) VALUES (?, ?, ?)";
    public static final String PREPARE_STATEMENT_INSERT = "INSERT INTO users (name, birthday) VALUES (?, ?)";
    public static final String INSERT_STRING = "INSERT INTO users (id, name, birthday) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_STRING = "SELECT id, name, birthday FROM users";
    private static final String SELECT_BY_ID_STRING = "SELECT id, name, birthday FROM users WHERE id = ?";

    private Connection connection;
    private Statement simpleInsertStatement;
    private PreparedStatement insertStatement;

    private PreparedStatement selectAllStatement;
    private PreparedStatement selectByStatement;
    private PreparedStatement transactionalPrepareStatement;
    private PreparedStatement transactionalPrepareStatement2;

    public UserWorker(Connection connection) {
        this.connection = connection;
        try {
            this.simpleInsertStatement = connection.createStatement();
            this.insertStatement = connection.prepareStatement(PREPARE_STATEMENT_INSERT_1);

            this.selectAllStatement = connection.prepareStatement(SELECT_ALL_STRING);
            this.selectByStatement = connection.prepareStatement(SELECT_BY_ID_STRING);
            this.transactionalPrepareStatement = connection.prepareStatement(INSERT_STRING);
            this.transactionalPrepareStatement2 = connection.prepareStatement(INSERT_STRING);
        } catch(SQLException e) {
            System.out.println("Can not create Statements. Reason:" + e.getMessage());
        }
    }

    public int simpleSaveUser(String query) {
        try {
            return this.simpleInsertStatement.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }

    public int saveUser(String name, LocalDate birthday) {
        try {
            this.insertStatement.setString(1, name);
            this.insertStatement.setDate(2, java.sql.Date.valueOf(birthday));
            return this.insertStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }

    public List<User> findAllUser () {
        List<User> users = new ArrayList<>();
        try(ResultSet resultSet = this.selectAllStatement.executeQuery()) {
            while(resultSet.next()) {
                User User = new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        LocalDate.parse(resultSet.getString("birthday")));
                users.add(User);
            }
        } catch(SQLException e) {
            System.out.println("Select ALL OldUser exception. Reason: " + e.getMessage());
        }
        return users;
    }

    public Optional<User> findUserById(Long id) {
        try {
            this.selectByStatement.setLong(1, id);
            try (ResultSet resultSet = this.selectByStatement.executeQuery()) {
                if(resultSet.next()) {
                    User User = new User(resultSet.getLong("id"),
                            resultSet.getString("name"),
                            LocalDate.parse(resultSet.getString("birthday")));
                    return Optional.of(User);
                }
            } catch(SQLException e) {
                System.out.println("Select user exception. Reason: " + e.getMessage());
            }
        } catch(SQLException e) {
            System.out.println("Select user exception. Reason: " + e.getMessage());
        }
        return Optional.empty();
    }

    public void saveAllUsers(List<User> users) {
        try {
            for(User user : users) {
                insertStatement.setString(1, user.getName());
                insertStatement.setDate(2, java.sql.Date.valueOf(user.getBirthday().toString()));
                insertStatement.addBatch();
            }
            insertStatement.executeBatch();
        } catch(SQLException e) {
            System.out.println("Insert ALL user exception. Reason: " + e.getMessage());
        }
    }

    public void saveAllUsersWithBatchSize(List<User> users, int batchSize) {
        Map<Long, List<User>> groups =
                users.stream().collect(Collectors.groupingBy(element -> (element.getId() - 1) / batchSize));
        List<List<User>> OldUsersBatches = new ArrayList<>(groups.values());
        try {
            for (List<User> batch : OldUsersBatches) {
                for(User User : batch) {
                    insertStatement.setLong(1, User.getId());
                    insertStatement.setString(2, User.getName());
                    insertStatement.setDate(3, java.sql.Date.valueOf(User.getBirthday().toString()));
                    insertStatement.addBatch();
                }
                insertStatement.executeBatch();
            }
        } catch(SQLException e) {
            System.out.println("Insert ALL User batches exception. Reason: " + e.getMessage());
        }
    }

    public List<User> generateTestUsers(long countUsersForButches, String name, int minusYears) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < countUsersForButches; i++) {
            users.add(new User( name + i, LocalDate.now().minusYears(minusYears)));
        }
        return users;
    }

    public List<User> generateTestUsers(long countUsersForButches, Long id, String name, int minusYears) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < countUsersForButches; i++) {
            users.add(new User( id + i, name + i, LocalDate.now().minusYears(minusYears)));
        }
        return users;
    }

    public void transactionalInsert(List<User> users1, List<User> users2) throws SQLException {
        try {
            this.connection.setAutoCommit(false);
            for(User User : users1) {
                transactionalPrepareStatement.setLong(1, User.getId());
                transactionalPrepareStatement.setString(2, User.getName());
                transactionalPrepareStatement.setDate(3, java.sql.Date.valueOf(User.getBirthday().toString()));
                transactionalPrepareStatement.addBatch();
            }
            for(User User : users2) {
                transactionalPrepareStatement2.setLong(1, User.getId());
                transactionalPrepareStatement2.setString(2, User.getName());
                transactionalPrepareStatement2.setDate(3, java.sql.Date.valueOf(User.getBirthday().toString()));
                transactionalPrepareStatement2.addBatch();
            }
            try {
                transactionalPrepareStatement.executeBatch();
                transactionalPrepareStatement2.executeBatch();
                connection.commit();
            } catch(SQLException e) {
                System.out.println("TRANSACTIONAL FAIL. Rollback changes. Reason: " + e.getMessage());
                connection.rollback();
                throw e;
            }
        } catch(SQLException e) {
            System.out.println("TRANSACTIONAL method SQL exception. Reason: " + e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
