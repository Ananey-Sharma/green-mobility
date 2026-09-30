package com.woxsen.greenmobility.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Central database connection utility.
 *
 * Configure the connection with environment variables before starting Tomcat:
 * DB_URL, DB_USER and DB_PASSWORD.
 */
public class DBUtil {

    private static final String URL = envOrDefault(
        "DB_URL",
        "jdbc:mysql://localhost:3306/green_mobility?useSSL=false&serverTimezone=UTC"
    );
    private static final String USER = envOrDefault("DB_USER", "greenmob");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    private static String envOrDefault(String name, String fallback) {
        String value = System.getenv(name);
        return value == null || value.isBlank() ? fallback : value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
