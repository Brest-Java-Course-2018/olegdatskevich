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
        dbUtils.addUser(connection, "manager", "Password01", "Manager");
        dbUtils.addUser(connection, "director", "Password02", "Director");
        dbUtils.getAllUsers(connection);
        dbUtils.removeUserById(connection, 2);
        dbUtils.getAllUsers(connection);
        dbUtils.updateUserById(connection, 3, "superuser", "superpassword", "SuperUser");
        dbUtils.getAllUsers(connection);
    }

    public static void displayMessage() {
        System.out.println("Hello World!");
    }
}