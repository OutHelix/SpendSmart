package com.outhelix.dataBase;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
    public static Connection getConnection() {
        String dbURL, dbUsername, dbPassword;
        String propertiesPath = "src/main/resources/config.properties";

        try (FileInputStream inputData = new FileInputStream(propertiesPath)){
            Properties properties = new Properties();
            properties.load(inputData);

            dbURL = properties.getProperty("dbURL");
            dbUsername = properties.getProperty("dbUsername");
            dbPassword = properties.getProperty("dbPassword");
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties file", e);
        }

        Connection connection = null;


        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Connection established");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }

        return connection;
    }

    public static void closeConnection (Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                throw new RuntimeException("Error closing connection", e);
            }
        }
    }
}
