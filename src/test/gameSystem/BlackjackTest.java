package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import database.Select;
import model.Game;
import model.User;
import model.playLog;

class BlackjackTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	@BeforeAll
	public static void setup() {
		 User user = new User("testId", "testName", "password", "password");
		 Insert insert = new Insert();
	     insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     request.setSession(session);
	}
	
	@BeforeEach
	public void setGame() {
		new Blackjack(10);
	}
	
	@Test 
	public void testBlackjack() {
		assertEquals("playerTurn.jsp", Blackjack.getGame(request));
	}
	
	@Test
	public void testGetGame() 
			throws ServletException, IOException {	
		Blackjack.Stand();
		assertEquals("result.jsp", Blackjack.getGame(request));
	}
	
	@Test
	public void testSetup() {
		Blackjack.Setup();
		String resultUrl = Blackjack.getGame(request); 
		
		assertEquals("playerTurn.jsp", resultUrl);
	}
	
	@Test
	public void testHit() {
		Blackjack.Hit();
		String resultUrl = Blackjack.getGame(request); 
		
		assertEquals("playerTurn.jsp", resultUrl);
	}
	
	@Test
	public void testStand() {
		String url = Blackjack.Stand();
		String resultUrl = Blackjack.getGame(request); 

		assertEquals("result.jsp", resultUrl);
	}
	
	@Test
	public void testSplit() {
		Blackjack.Split();
		String resultUrl = Blackjack.getGame(request); 

		assertEquals("result.jsp", resultUrl);
	}
	
	@AfterAll
	public static void clean() {
		Delete delete = new Delete();
		delete.deleteUser("testId");
		delete.deleteLog("testId");
	}

}
