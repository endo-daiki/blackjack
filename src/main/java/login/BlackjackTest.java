package login;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
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
import gameSystem.Blackjack;
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
	public void testResetGame() {
		Blackjack.resetGame();
		
		Game game = Blackjack.game;
		
		assertNull(game);
	}
	
	@Test
	public void testGetGame() {
		Game game = Blackjack.game;
		game.setResult("win");
		
		Blackjack.getGame(request);
		
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
