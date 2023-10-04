package org.example.config;

import org.example.elements.TestData;
import org.example.props.PropertyReader;

import java.sql.*;

public class H2Database {

    private static final H2Database INSTANCE = new H2Database();

    private Connection h2Connection;
    private H2Database () {

        try {
            // H2
//            String h2ConnectionUrl = "jdbc:h2:./test";
            String h2ConnectionUrl = PropertyReader.getConnectionUrlForH2();
            // can use h2 connection in our mode without login and password
            this.h2Connection = DriverManager.getConnection(h2ConnectionUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception");
        }
    }

    public static H2Database getInstance() {
        return INSTANCE;
    }

    public Connection getH2Connection () {
        return h2Connection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = h2Connection.createStatement()) {
            return statement.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void executeResult(String query) {
        try(Statement statement = h2Connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                TestData td = new TestData(resultSet.getInt("id"), resultSet.getString("name"));
                System.out.println("------>>>> " + td.toString());
            }
        } catch(SQLException e) {
            System.out.println(String.format("Exception. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not run query.");
        }
    }

    public void closeConnection() {
        try {
            h2Connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
