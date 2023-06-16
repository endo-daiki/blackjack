package gameSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;

class SplitTest {
static Game game;
	
	@BeforeEach
	public void setup() {
		game = new Game(10);
	}
	
	@Test 
	public void testSplit() {
		Deck deck = new Deck();
		game.setDeck(deck);

		Setup.excute(game);
		assertEquals("PlayerTurn", Split.excute(game));
	}

	@Test
	public void testBjSplit() {
		Deck deck = new Deck();
		game.setDeck(deck);

		Setup.excute(game);

		assertEquals("PlayerTurn", Split.excute(game));
	}
}
