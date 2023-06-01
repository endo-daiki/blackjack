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
import model.Game;
import model.User;

class HitTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	@BeforeAll
	public static void setup() {
		 User user = new User("testId", "testName", "password", "password");
		 Insert insert = new Insert();
	     insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     new Blackjack(10);
	     request.setSession(session);
	}
	
	@Test 
	public void testHit() {
		Game game = new Game(10);
		
		new Hit(game);
		String url = Hit.getUrl();
		assertEquals("PlayerTurn", url);
		
		Player player = game.getPlayer();
		Card card = new Card("heart", CardNumber.king);
		player.getPoint().calc(card);
		player.getPoint().calc(card);

		url = Hit.getUrl();
		assertEquals("Result", url);
	}
	
	@AfterAll
	public static void clean() {
		Delete delete = new Delete();
		delete.deleteUser("testId");
		delete.deleteLog("testId");
	}
}
