package Projet_Calbo.config;

import static org.junit.Assert.*;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @Before
    public void setUp() throws Exception {
        databaseConnection = DatabaseConnection.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        if (databaseConnection != null && !databaseConnection.getConnection().isClosed()) {
            databaseConnection.getConnection().close();
        }
    }

    @Test
    public void testGetInstance() throws Exception {
        assertNotNull(databaseConnection);
        
        DatabaseConnection anotherInstance = DatabaseConnection.getInstance();
        assertSame(databaseConnection, anotherInstance);
    }

    @Test
    public void testGetConnection() throws Exception {
        
        Connection connection = databaseConnection.getConnection();
        assertNotNull(connection);
        
        assertTrue(connection.isValid(2)); 
    }
}
