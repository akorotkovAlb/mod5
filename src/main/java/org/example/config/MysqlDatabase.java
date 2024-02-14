package org.example.config;

import org.example.elements.TestData;
import org.example.props.PropertyReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MysqlDatabase {

    private static final MysqlDatabase INSTANCE = new MysqlDatabase();

    private Connection mysqlConnection;
    private MysqlDatabase () {

        try {
            String mysqlConnectionUrl = PropertyReader.getConnectionUrlForMysql();
            this.mysqlConnection = DriverManager.getConnection(mysqlConnectionUrl,
                    PropertyReader.getUserForMysql(),
                    PropertyReader.getPasswordForMysql());
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception ==> " + e.getMessage());
        }
    }

    public static MysqlDatabase getInstance() {
        return INSTANCE;
    }

    public Connection getMysqlConnection () {
        return mysqlConnection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = mysqlConnection.createStatement()) {
            return statement.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void executeResult(String query) {
        try(Statement statement = mysqlConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                TestData td = new TestData(resultSet.getInt("id"), resultSet.getString("name"));
                System.out.println("mysql ------>>>> " + td.toString());
            }
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void execute(String fileName) {
        try(Statement statement = mysqlConnection.createStatement()) {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            statement.execute(content);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            mysqlConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
