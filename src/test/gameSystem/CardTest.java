package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CardTest {
	static Card card;

	@BeforeAll
	public static void setup() {
		card = new Card("heart", CardNumber.one);
	}

	@Test
	public void testCard() {
		assertNotNull(card);
		assertEquals(11, card.getNo().getPoint());
	}


	@Test
	public void testGetSuit() {
		assertEquals("heart", card.getSuit());
	}

	@Test
	public void testGetNo() {
		CardNumber no = card.getNo();
		assertEquals(11, no.getPoint());
	}

	@Test
	public void testCourtCheck() {
		assertEquals(false, card.courtCheck());

		Card testCard = new Card("heart", CardNumber.king);
		assertEquals(true, testCard.courtCheck());
	}


}
