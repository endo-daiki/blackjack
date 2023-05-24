package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;

class DeckTest {

	@Test
	public void testDeckInstanse() {
		Deck deck = new Deck();
		assertNotNull(deck);
	}
	
	@Test
	public void testDraw() {
		Deck deck = new Deck();
		Card card = deck.Draw();
		
		assertNotNull(card);
	}

}
