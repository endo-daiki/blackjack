package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.User;
import model.playLog;

class DeleteTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	static Delete delete = new Delete();
	
	@BeforeAll
	public static void setup() {
		Insert insert = new Insert();
		User user = new User("testId", "testName", "password", "password");
	    insert.insertUser(user.getId(), user.getName(), user.getPassword());	     
	    insert.insertLog("testId", 20);
	}
	
	@Test
	public void testDeleteUser() {	
		boolean checker = delete.deleteUser("damyId");	
		assertEquals(true, checker);
	}
	
	@Test
	public void testDeleteLog() {
		boolean checker = delete.deleteLog("testId");
		assertEquals(true, checker);
	}
	
	@AfterAll
	public static void clean() {
		delete.deleteUser("testId");
		delete.deleteLog("testId");
	}
	
}
