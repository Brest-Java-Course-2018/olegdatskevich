package com.epam.brest.cource;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    /**
     * The method redirect standard output to byte array.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    /**
     * The method compares the redirected output stream with the expected message.
     */
    @Test
    public void testDisplayMessage() {
        App.displayMessage();
        assertEquals("Hello World!\n", output.toString());
    }

    /**
     * Returns the standard output to its place.
     */
    @After
    public void cleanUpStream() {
        System.setOut(null);
    }
}
