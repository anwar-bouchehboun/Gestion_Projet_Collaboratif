package Projet_Calbo.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private String DB_URL = "jdbc:mysql://localhost:3306/collaboratif"; 
    private String USER = "root"; 
    private String PASS = "15987463";  

 

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            //LoggerMessage.debug("Failed to connect to the database: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
        } catch (SQLException e) {
            //LoggerMessage.error("Failed to re-establish the database connection: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                //LoggerMessage.info("Database connection closed");
            } catch (SQLException e) {
               // LoggerMessage.error("Failed to close the database connection: " + e.getMessage());
            }
        }
    }
}















