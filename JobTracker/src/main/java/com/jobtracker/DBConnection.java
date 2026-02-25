package com.jobtracker;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        // If running locally, use localhost database
        if (url == null || user == null || password == null) {
            url = "jdbc:mysql://localhost:3306/jobtracker";
            user = "root";
            password = "Rp@160510";
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}