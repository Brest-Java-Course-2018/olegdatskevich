package com.epam.brest.cource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtilsTest {

    private final DBUtils dbUtils = new DBUtils();

    @Test
    public void testGetConnection() throws SQLException, ClassNotFoundException {
        assertNotNull(dbUtils.getConnection());
    }

    @Test
    public void testAddUser() throws SQLException, ClassNotFoundException {
        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);

        assertEquals(1, dbUtils.addUser(connection, "human", "pass", "Human"));
    }
}
