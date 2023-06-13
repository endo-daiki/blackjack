package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
import model.Game;
import model.User;

class BlackjackTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();

	@BeforeAll
	public static void setup() {
		User user = new User("testId", "testName", "password", "password");
		Insert.insertUser(user.getId(), user.getName(), user.getPassword());

		session.setAttribute("user", user);
		request.setSession(session);
	}

	@BeforeEach
	public void setGame() {
		new Blackjack(10, "testId");
	}

	@Test 
	public void testBlackjack() {
		Blackjack.getPlayerTurn(request);
		Game game = (Game)request.getAttribute("game");
		Player player = game.getPlayer();
		
		
	}

	@Test
	public void testGetGame() 
			throws ServletException, IOException {	
		Blackjack.Stand("normal");
		assertEquals("result.jsp", Blackjack.getPlayerTurn(request));
	}

	@Test
	public void testSetup() {
		Blackjack.Setup();
		String resultUrl = Blackjack.getPlayerTurn(request); 

		assertEquals("playerTurn.jsp", resultUrl);
	}

	@Test
	public void testHit() {
		Blackjack.Hit("normal");
		String resultUrl = Blackjack.getPlayerTurn(request); 

		assertEquals("playerTurn.jsp", resultUrl);
	}

	@Test
	public void testStand() {
		Blackjack.Stand("normal");
		String resultUrl = Blackjack.getPlayerTurn(request); 

		assertEquals("result.jsp", resultUrl);
	}

	@Test
	public void testSplit() {
		Blackjack.Setup();

		Blackjack.Split();
		String resultUrl = Blackjack.getPlayerTurn(request); 

		assertEquals("playerTurn.jsp", resultUrl);
	}

	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}

}
