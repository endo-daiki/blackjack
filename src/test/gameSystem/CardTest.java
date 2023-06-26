package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CardTest {
	static Card card;

	@BeforeAll
	public static void setup() {
		card = new Card(Suit.heart, CardNumber.one);
	}

	@Test
	public void testCard() {
		assertNotNull(card);
		assertEquals(11, card.cardNumber.getPoint());
	}

	@Test
	public void testCourtCheck() {
		assertFalse(card.courtCheck());

		Card kingCard = new Card(Suit.heart, CardNumber.king);
		Card queenCard = new Card(Suit.heart, CardNumber.queen);
		Card jackCard = new Card(Suit.heart, CardNumber.jack);
		assertTrue(kingCard.courtCheck());
		assertTrue(queenCard.courtCheck());
		assertTrue(jackCard.courtCheck());
	}
	
	@Test
	public void testEqualCheck() {
		Card card2 = new Card(Suit.diamond, CardNumber.one);
		assertTrue(card.equalCheck(card2));
		
		card2 = new Card(Suit.heart, CardNumber.king);
		assertFalse(card.equalCheck(card2));
	}
}
