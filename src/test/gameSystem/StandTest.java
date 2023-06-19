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
		
		assertEquals("Result", Stand.excute(game, "PLAYING"));
	}

	@Test
	public void testSplitStand() {
		Deck deck = new Deck() {
			@Override
			public Card pull() { //必ずaceを引くとする
				return new Card("heart", CardNumber.one);
			}
		};
		game.setDeck(deck);

		Setup.excute(game);
		Split.excute(game);

		assertEquals("PlayerTurn", Stand.excute(game, "PLAYING"));
		assertEquals("Result", Stand.excute(game, "SPLIT"));
	}
}
