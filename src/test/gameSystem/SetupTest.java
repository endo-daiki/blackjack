package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import model.User;

class SetupTest {	
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	@BeforeAll
	public static void setup() {
		 User user = new User("testId", "testName", "password", "password");
	     Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     new Blackjack("testId");
	     request.setSession(session);
	}
	
	@Test 
	public void testSetup() {
		String url = Blackjack.Setup();			
		assertEquals("PlayerTurn", url);
		
		while(!(url == "Result")) {
			new Blackjack("testId");
			url = Blackjack.Setup();
		}
		
		assertNotEquals("playing",Blackjack.game.getResult());
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}
}
