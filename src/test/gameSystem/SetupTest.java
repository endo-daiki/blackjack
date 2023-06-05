package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class SetupTest {	
	@Test 
	public void testSetup() {
		Game game = new Game(10);
		Deck deck = new Deck();
		Card card = new Card("heart", CardNumber.two);
		deck.add(card);
		deck.add(card);

		game.setDeck(deck);

		assertEquals("PlayerTurn", Setup.excute(game));
	}

	@Test
	public void testBjSetup() {
		Game game = new Game(10);
		Deck deck = new Deck();
		Card kingCard = new Card("heart", CardNumber.king);
		Card aceCard = new Card("heart", CardNumber.one);
		deck.add(kingCard);
		deck.add(aceCard);

		game.setDeck(deck);

		assertEquals("Result", Setup.excute(game));
	}
}
