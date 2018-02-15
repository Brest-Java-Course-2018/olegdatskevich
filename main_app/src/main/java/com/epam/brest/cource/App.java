package com.epam.brest.cource;

public class App {
    public static void main(String[] args) {
        displayMessage();
        DBUtils dbUtils = new DBUtils();
    }

    public static void displayMessage() {
        System.out.println("Hello World!");
    }
}