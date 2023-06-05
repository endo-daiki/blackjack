package database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.User;

class DeleteTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
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
