package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class HandTest {
	static Hand hand;
	static Deck deck;
	
	@BeforeEach
	public void setup() {
		hand = new Hand();
		deck = new Deck();
	}
	
	@Test
	public void testHand() {
		assertNotNull(hand);
		assertEquals(false, hand.splitCheck());
	}
	
	@Test
	public void testDraw() {
		hand.draw(deck);
		List<Card> list = hand.getList();
			
		assertEquals(1, list.size());
	}
	
	@Test
	public void testGetList() {
		List<Card> list = hand.getList();	
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSplitCheck() {
		hand.draw(deck);
		hand.draw(deck);
		assertEquals(false, hand.splitCheck());

		hand.draw(deck);
		assertEquals(false, hand.splitCheck());
	}

}