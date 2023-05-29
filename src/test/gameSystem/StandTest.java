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

class StandTest {
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
	public void testStand() {
		String url = Blackjack.Stand();	
		assertEquals("Result", url);
		
		Player player = new Player();
		Player dealer = new Player();
		
		player.getPoint().calc(new Card("heart", CardNumber.king));
		player.getPoint().calc(new Card("heart", CardNumber.king));
		dealer.getPoint().calc(new Card("heart", CardNumber.king));
		dealer.getPoint().calc(new Card("heart", CardNumber.seven));
		Blackjack.game.setPlayer(player);
		Blackjack.game.setDealer(dealer);
		url = Blackjack.Stand();
		assertEquals("win", Blackjack.game.getResult());
		
		dealer.getPoint().calc(new Card("heart", CardNumber.four));
		Blackjack.game.setDealer(dealer);
		url = Blackjack.Stand();
		assertEquals("lose", Blackjack.game.getResult());
		
		player.getPoint().calc(new Card("heart", CardNumber.one));
		Blackjack.game.setPlayer(player);
		url = Blackjack.Stand();
		assertEquals("draw", Blackjack.game.getResult());
		
		player.getPoint().calc(new Card("heart", CardNumber.king));	
		Blackjack.game.setPlayer(player);
		url = Blackjack.Stand();
		
		assertEquals("Result", url);
		assertEquals("lose", Blackjack.game.getResult());
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}
}
