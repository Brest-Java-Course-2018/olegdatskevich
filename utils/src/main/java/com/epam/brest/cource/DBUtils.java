package com.epam.brest.cource;

import java.sql.*;
import java.util.Scanner;

public class DBUtils {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        System.out.println("Connect to DB");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(databaseURL, "sa", "");
        return connection;
    }

    public void createUserTable(Connection connection) throws SQLException {
        System.out.println("Create user table");
        String createTable =
                "CREATE TABLE app_user (" +
                        "user_id INT NOT NULL AUTO_INCREMENT, " +
                        "login VARCHAR (255) NOT NULL, " +
                        "password VARCHAR (255) NOT NULL, " +
                        "description VARCHAR (255) NULL, " +
                        "PRIMARY KEY (user_id)" +
                        ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTable);
        }
    }

    public void addUser(Connection connection, String login, String password, String description) throws SQLException {
        System.out.println(String.format("Add user: %s", login));

        String newUser = "INSERT INTO app_user (login, password, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);
        preparedStatement.executeUpdate();
    }

    public void getAllUsers(Connection connection) throws SQLException {
        System.out.println("Read users:");

        String getRecords = "SELECT user_id, login, description FROM app_user ORDER BY user_id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getRecords);
        while (resultSet.next()) {
            System.out.println(String.format("User %s: %s, %s.",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")));
        }
    }

    public void delUser(Connection connection, int user_id) throws SQLException {
        System.out.print("Delete user: ");
        getUser(connection, user_id);
        System.out.print("y/n ");
        Scanner dataString = new Scanner(System.in);
        if (dataString.hasNext("y")) {
            String delRecord = "DELETE FROM app_user WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(delRecord);
            preparedStatement.setInt(1,user_id);
            preparedStatement.executeUpdate();
            System.out.println("User deleted.");
        } else {
            System.out.println("User wasn't deleted.");
        }
    }

    public void getUser(Connection connection, int user_id) throws SQLException {
        String outputUser = "SELECT user_id, login, description FROM app_user WHERE user_id = " + user_id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(outputUser);
        if (resultSet.next()) {
            System.out.println(String.format("User %s: %s, %s.",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")));
        }

    }
}