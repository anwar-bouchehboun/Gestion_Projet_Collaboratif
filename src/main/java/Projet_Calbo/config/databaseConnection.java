package Projet_Calbo.config;

public class databaseConnection {
	package Projet_Calbo.config;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.logging.Level;
	import java.util.logging.Logger;

	public class DatabaseConnection {

	    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
	    private static DatabaseConnection instance;
	    private final Connection connection;
	    private static final String URL = "jdbc:mysql://localhost:3306/collaboratif";
	    private static final String USER = "mysql";
	    private static final String PASSWORD = "password";

	    private DatabaseConnection() {
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            LOGGER.info("Connexion à la base de données établie avec succès.");
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Erreur de connexion à la base de données", e);
	            throw new RuntimeException("Erreur de connexion à la base de données", e);
	        }
	    }

	    public static synchronized DatabaseConnection getInstance() {
	        if (instance == null) {
	            instance = new DatabaseConnection();
	        }
	        return instance;
	    }

	    public Connection getConnection() {
	        return connection;
	    }

	    public static void testConnection() {
	        DatabaseConnection dbConnection = getInstance();
	        Connection connection = dbConnection.getConnection();

	        try {
	            if (connection != null && !connection.isClosed()) {
	                System.out.println("Connexion à la base de données réussie.");
	            } else {
	                System.out.println("La connexion à la base de données est fermée ou a échoué.");
	            }
	        } catch (SQLException e) {
	            LOGGER.log(Level.SEVERE, "Erreur lors de la vérification de la connexion", e);
	        }
	    }

	}
}
