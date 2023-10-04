package org.example;

import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main (String[] args) throws SQLException, IOException {

//        String createTable = "CREATE TABLE test_table (id int PRIMARY KEY, name VARCHAR(100))";
//        String insert1 = "INSERT INTO test_table VALUES (1, 'TestName'), (2, 'Tony')";
//        String insert2 = "INSERT INTO test_table VALUES (3, 'Piter'), (4, 'Ivan'), (5, 'Taras')";
//        String select1 = "SELECT * FROM test_table";
//
//        PostgresDatabase instance = PostgresDatabase.getInstance();
//        int res1 = instance.executeUpdate(createTable);
//        int res2 = instance.executeUpdate(insert1);
//        int res3 = instance.executeUpdate(insert2);
//        System.out.println("===> " + res1);
//        System.out.println("===> " + res2);
//        System.out.println("===> " + res3);
//
//        instance.executeResult(select1);
//
//        instance.closeConnection();



//        Postgres1Database instance = Postgres1Database.getInstance();
//        String sqlFilePath = "src/main/resources/file.sql";
//        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
//            StringBuffer sb= new StringBuffer();
//            reader.lines().forEach(sb::append);
//            instance.executeUpdate(sb.toString());
//        } catch(Exception e) {
//            System.out.println("fail");
//        }

        //TODO  first step
//        // H2
//        String h2ConnectionUrl = "jdbc:h2:./test";
//        // can use h2 connection in our mode without login and password
//        Connection h2Connection = DriverManager.getConnection(h2ConnectionUrl);
//        Statement h2Statement = h2Connection.createStatement();
//
//        h2Statement.executeUpdate("CREATE TABLE test_table (id int PRIMARY KEY, name VARCHAR(100))");




        // TODO after modifying
//        H2Database h2Database = H2Database.getInstance();
//        h2Database.executeUpdate("CREATE TABLE test_table (id int PRIMARY KEY, name VARCHAR(100))");



//        String selectQuery = "SELECT * FROM test_table";
//        H2Database h2Database = H2Database.getInstance();
//        ResultSet resultSet = h2Database.executeQuery(selectQuery);
//
//        if (resultSet.next()) {
//            Long id = resultSet.getLong("id");
//        }

    }
}