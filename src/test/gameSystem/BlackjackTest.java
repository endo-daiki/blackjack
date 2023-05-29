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
	     Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     request.setSession(session);
	}
	
	@BeforeEach
	public void setGame() {
		new Blackjack("testId");
	}
	
	@Test 
	public void testBlackjack() {
		Game game = Blackjack.game;
		
		game.getUserId();
		
		assertEquals("testId", game.getUserId());
		assertEquals("playing", game.getResult());
	}
	
	@Test
	public void testGetGame() 
			throws ServletException, IOException {

		Game game = Blackjack.game;
		game.setResult("playing");
		
		RequestDispatcher dispatcher = Blackjack.getGame(request);
		dispatcher.forward(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("playerTurn.jsp", url);
		
		game.setResult("win");
		
		dispatcher = Blackjack.getGame(request);
		dispatcher.forward(request, response);
		
		List<playLog> playLog = Select.selectPlayLog("testId");
		String result = playLog.get(0).getLog();
		
		game = (Game)request.getAttribute("game");
		assertNotNull(game);
		assertEquals("win", result);
	}
	
	@Test
	public void testSetup() {
		Blackjack.Setup();
		Game game = Blackjack.game;
		String result = game.getResult();
		
		assertEquals("playing", result);
	}
	
	@Test
	public void testHit() {
		Blackjack.Hit();
		Game game = Blackjack.game;
		String result = game.getResult();
		
		assertEquals("playing", result);
	}
	
	@Test
	public void testStand() {
		String url = Blackjack.Stand();

		assertEquals("Result", url);
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}

}
