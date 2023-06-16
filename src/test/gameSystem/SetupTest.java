package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class SetupTest {	
	@Test 
	public void testSetup() {
		Game game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);

		assertEquals("PlayerTurn", Setup.excute(game));
	}

	@Test
	public void testBjSetup() {
		Game game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);

		assertEquals("Result", Setup.excute(game));
	}
}
