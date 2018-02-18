package com.epam.brest.cource;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        displayMessage();
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        dbUtils.addUser(connection, "admin", "admin", "Administrator");
        dbUtils.addUser(connection, "user1", "Password01", "User 1");
        dbUtils.addUser(connection, "user2", "Password02", "User 2");
        dbUtils.getAllUsers(connection);
        dbUtils.delUser(connection, 2);
        dbUtils.getAllUsers(connection);
    }

    public static void displayMessage() {
        System.out.println("Hello World!");
    }
}