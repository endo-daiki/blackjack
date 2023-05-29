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

	@Test 
	public void testInsertUser() {
		boolean checker = Insert.insertUser("testId", "testName", "password");
		
		User user = Select.selectUser("testId", "password");
		
		assertEquals(true, checker);
		assertEquals("testName", user.getName());
	}
	
	@Test
	public void testInsertLog() {
		boolean checker = Insert.insertLog("testId", "win");
		
		List<playLog> playLogs = Select.selectPlayLog("testId");
		
		assertEquals(true, checker);
		assertEquals(1, playLogs.size());
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}
}
