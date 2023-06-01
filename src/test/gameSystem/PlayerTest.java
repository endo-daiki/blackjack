package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlayerTest {
	static Player player;
	
	@BeforeAll
	public static void setup() {
		player = new Player();
	}
	
	@Test
	public void testPlayer() {
		assertNotNull(player);
	}
	
	@Test
	public void testDraw() {
		Deck deck = new Deck();
		player.draw(deck);
		
		Hand hand = player.getHand();
		assertEquals(false, hand.splitCheck());
	}
	
	@Test
	public void testGetHand() {
		Hand hand = player.getHand();
		assertNotNull(hand);
	}
	
	@Test
	public void testGetPoint() {
		Point point = player.getPoint();
		assertNotNull(point);
	}

}
