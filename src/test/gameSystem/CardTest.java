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
		assertFalse(card.courtCheck()); //絵札のカードではないのでfalse

		//絵札すべてが正しく判定されるかチェック
		Card kingCard = new Card(Suit.heart, CardNumber.king);
		Card queenCard = new Card(Suit.heart, CardNumber.queen);
		Card jackCard = new Card(Suit.heart, CardNumber.jack);
		assertTrue(kingCard.courtCheck());
		assertTrue(queenCard.courtCheck());
		assertTrue(jackCard.courtCheck());
	}
	
	@Test
	public void testPointCheck() { //スプリットできるか判定するため、点数が同じかチェックする
		Card card2 = new Card(Suit.diamond, CardNumber.one);
		assertTrue(card.pointCheck(card2));
		
		card2 = new Card(Suit.heart, CardNumber.king);
		assertFalse(card.pointCheck(card2));
	}
	
	@Test
	public void equalsCheck() {
		Card card2 = card;
		
		assertTrue(card.equals(card2));
		
		card2 = null;
		assertFalse(card.equals(card2));
		
		String a = "test";
		assertFalse(card.equals(a));
		
		card2 = new Card(Suit.heart, CardNumber.one);
		assertTrue(card.equals(card2));
		
		card2 = new Card(Suit.diamond, CardNumber.one);
		assertFalse(card.equals(card2));
		card2 = new Card(Suit.heart, CardNumber.king);
		assertFalse(card.equals(card2));
	}
}
