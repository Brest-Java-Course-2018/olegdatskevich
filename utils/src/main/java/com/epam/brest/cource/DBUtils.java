package com.epam.brest.cource;

import java.sql.*;
import java.util.Scanner;

/**
 * Work with DB.
 * CRUD methods.
 */
public class DBUtils {
    /**
     * Connect to DB
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        System.out.println("Connect to DB.");
        String databaseURL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(databaseURL, "sa", "");
        return connection;
    }

    /**
     * Create table app_user.
     * @param connection
     * @return 0 if table was created.
     * @throws SQLException
     */
    public int createUserTable(Connection connection) throws SQLException {
        System.out.println("Create user table.");
        String createTable =
                "CREATE TABLE app_user ("
                        + "user_id INT NOT NULL AUTO_INCREMENT, "
                        + "login VARCHAR (255) NOT NULL, "
                        + "password VARCHAR (255) NOT NULL, "
                        + "description VARCHAR (255) NULL, "
                        + "PRIMARY KEY (user_id)"
                        + ")";
        try (Statement statement = connection.createStatement()) {
            int result = statement.executeUpdate(createTable);
            return result;
        }
    }

    /**
     * Adding a user in DB.
     * @param connection
     * @param login
     * @param password
     * @param description
     * @return 1 if user was added.
     * @throws SQLException
     */
    public int addUser(Connection connection, String login, String password, String description) throws SQLException {
        System.out.println("Adding a user.");

        String newUser = "INSERT INTO app_user (login, password, description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);
        System.out.println(String.format("Added user: %s", login));
        int resultAddedUser = preparedStatement.executeUpdate();
        return resultAddedUser;
    }

    /**
     * Print one user from DB.
     * @param connection
     * @param user_id
     * @throws SQLException
     */
    public void getUserById(Connection connection, int user_id) throws SQLException {
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

    /**
     * Print all users from DB.
     * @param connection
     * @throws SQLException
     */
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

    /**
     * Remove user from DB.
     * @param connection
     * @param user_id - number user in DB.
     * @throws SQLException
     */
    public void removeUserById(Connection connection, int user_id) throws SQLException {
        System.out.print("Delete user: ");
        getUserById(connection, user_id);

        System.out.print("y/n ");
        Scanner dataString = new Scanner(System.in);
        String delRecord = "DELETE FROM app_user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delRecord);
        if (dataString.hasNext("y")) {
            preparedStatement.setInt(1,user_id);
            System.out.println("User deleted.");
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Update user information.
     * @param connection
     * @param user_id
     * @param login
     * @param password
     * @param description
     * @throws SQLException
     */
    public void updateUserById(Connection connection, int user_id, String login, String password, String description) throws SQLException {
        System.out.print("Update user: ");
        getUserById(connection, user_id);

        String updateUser = "UPDATE app_user SET login = ?, password = ?, description = ? WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateUser);
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, description);
        preparedStatement.setInt(4, user_id);
        preparedStatement.executeUpdate();
    }
}
