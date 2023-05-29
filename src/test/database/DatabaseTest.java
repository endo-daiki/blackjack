package database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	@Test
	public void testGetConnection() {
		new Database();
		Connection con = Database.getConnection();
		assertNotNull(con);
	}

}
