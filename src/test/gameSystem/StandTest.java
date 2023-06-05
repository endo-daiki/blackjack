package gameSystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;

class StandTest {	
	static Game game;
	
	@BeforeEach
	public void setup() {
		game = new Game(10);
	}
	@Test 
	public void testStand() {
		Setup.excute(game);
		
		assertEquals("Result", Stand.excute(game));
	}

	@Test
	public void testSplitStand() {
		Card card = new Card("heart", CardNumber.one);
		Deck deck = new Deck();

		deck.add(card);
		deck.add(card);
		game.setDeck(deck);

		Setup.excute(game);
		Split.excute(game);

		assertEquals("PlayerTurn", Stand.excute(game));
	}
}
