package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.User;

class DeleteTest {
	@BeforeAll
	public static void setup() {
		User user = new User("testId", "testName", "password", "password");
		Insert.insertUser(user.getId(), user.getName(), user.getPassword());	     
		Insert.insertLog("testId", 20);
	}

	@Test
	public void testDeleteUser() {	
		boolean checker = Delete.deleteUser("testId");	
		assertEquals(true, checker);
	}

	@Test
	public void testDeleteLog() {
		boolean checker = Delete.deleteLog("testId");
		assertEquals(true, checker);
	}	
}
