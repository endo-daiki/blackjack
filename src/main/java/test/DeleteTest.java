package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import database.Select;
import gameSystem.Blackjack;
import model.User;
import model.playLog;

class DeleteTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	@BeforeAll
	public static void setup() {
		 User user = new User("testId", "testName", "password", "password");
	     Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Insert.insertLog("testId", "win");
	}
	
	@Test
	public void testDeleteUser() {
		Delete.deleteUser("testId");
		
		boolean checker = Select.selectId("testId");		
		assertEquals(false, checker);
	}
	
	@Test
	public void testDeleteLog() {
		Delete.deleteLog("testId");
		
		List<playLog> playLogs = Select.selectPlayLog("testId");
		assertEquals(0, playLogs.size());
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}
	
}
