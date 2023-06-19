package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

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
	public void testGetPlayerTurn() {
		assertEquals("playerTurn.jsp", Blackjack.getPlayerTurn(request));
	}

	@Test
	public void testGetResult() {	
		assertEquals("result.jsp", Blackjack.getResult(request));
	}

	@Test
	public void testSetup() {
		assertEquals("PlayerTurn", Blackjack.Setup());
	}

	@Test
	public void testHit() {
		assertEquals("PlayerTurn", Blackjack.Hit("PLAYING"));
	}

	@Test
	public void testStand() {
		assertEquals("Result", Blackjack.Stand("PLAYING"));
	}

	@Test
	public void testSplit() {
		Blackjack.Setup();
		assertEquals("PlayerTurn", Blackjack.Split());
	}

	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}

}
