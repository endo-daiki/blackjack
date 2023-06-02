package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.User;
import model.playLog;

class InsertTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	static Insert insert = new Insert();
	static String id = "testId";
	static String name = "testName";
	static String password = "password";
	static int tip = 20;

	@Test 
	public void testInsertUser() {
		boolean checker = insert.insertUser(id, name, password);	
		assertEquals(true, checker);
	}
	
	@Test
	public void testInsertLog() {
		boolean checker = insert.insertLog(id, tip);
		assertEquals(true, checker);
	}
	
	@AfterAll
	public static void clean() {
		Delete delete = new Delete(); 
		delete.deleteUser(id);
		delete.deleteLog(id);
	}
}
