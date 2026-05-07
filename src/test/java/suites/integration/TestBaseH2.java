package suites.integration;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.testng.Assert.*;

public class TestBaseH2 {
    private Connection conn;

    @BeforeMethod
    public void setUp() throws Exception {
        conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        try (PreparedStatement stmt = conn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(50))")) {
            stmt.executeUpdate();
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if (conn != null) conn.close();
    }

    @Test
    public void testInsertAndSelect() throws Exception {
        PreparedStatement insert = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
        insert.setInt(1, 1);
        insert.setString(2, "Alice");
        insert.executeUpdate();
        insert.close();

        PreparedStatement select = conn.prepareStatement("SELECT name FROM users WHERE id = 1");
        ResultSet rs = select.executeQuery();
        assertTrue(rs.next());
        assertEquals(rs.getString("name"), "Alice");
        rs.close();
        select.close();
    }

    @Test(expectedExceptions = org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException.class)
    public void testDuplicateKey() throws Exception {
        PreparedStatement insert = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
        insert.setInt(1, 1);
        insert.setString(2, "Bob");
        insert.executeUpdate();
        insert.close();
        // Deuxième insertion avec le même ID doit lever une exception
        insert = conn.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)");
        insert.setInt(1, 1);
        insert.setString(2, "Charlie");
        insert.executeUpdate();
    }
}