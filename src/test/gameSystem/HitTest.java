package gameSystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class HitTest {
	static Game game;
	
	@Test
	public void testHit() {
		game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);
		
		Setup.excute(game);
		assertEquals("PlayerTurn", Hit.excute(game, "PLAYING"));
	}	

	@Test
	public void testBurstHit() {
		game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);
		
		Setup.excute(game);
		assertEquals("Result", Hit.excute(game, "PLAYING"));
	}	

	@Test
	public void testSplitHit() {
		game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game, "PLAYING"));
	}

	@Test
	public void testSplitBurstHit() {
		game = new Game(10);
		Deck deck = new Deck();
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game, "PLAYING"));
	}
}
