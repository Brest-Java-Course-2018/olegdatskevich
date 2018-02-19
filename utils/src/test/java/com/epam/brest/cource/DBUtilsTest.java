package com.epam.brest.cource;

import static org.junit.Assert.*;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsTest {

    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        assertNotNull(dbUtils.getConnection());
    }

    @Test
    public void testAddUser() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);

        assertEquals(1, dbUtils.addUser(connection, "human", "pass", "Human"));
    }
}