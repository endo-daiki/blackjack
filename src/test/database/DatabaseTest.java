package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;


class DatabaseTest {    
	@Test
	public void testGetConnection() throws SQLException {
		new Database();
		Connection con = Database.getConnection();
		assertNotNull(con);
	}
}
