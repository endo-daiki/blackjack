package gameSystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;

class HitTest {
	static Game game;

	@BeforeEach
	public void setup() {
		game = new Game(10);
	}
	
	@Test 
	public void testHit() {
		assertEquals("PlayerTurn", Hit.excute(game));
		
		Player player = game.getPlayer();
		Card card = new Card("heart", CardNumber.king);
		player.getPoint().calc(card);
		player.getPoint().calc(card);
		game.setPlayer(player);
		
		assertEquals("Result", Hit.excute(game));
	}

	@Test
	public void testBurstHit() {
		Card card = new Card("heart", CardNumber.king);
		Deck deck = new Deck();

		deck.add(card);
		deck.add(card);
		game.setDeck(deck);

		Hit.excute(game);
		Hit.excute(game);
		assertEquals("Result", Hit.excute(game));
	}

	@Test
	public void testSplitHit() {
		Card card = new Card("heart", CardNumber.one);
		Deck deck = new Deck();

		deck.add(card);
		deck.add(card);
		game.setDeck(deck);

		Setup.excute(game);
		Split.excute(game);

		assertEquals("PlayerTurn", Hit.excute(game));
	}

	@Test
	public void testSplitBurst() {
		Deck deck = new Deck();
		
		Card card = new Card("heart", CardNumber.nine);
		deck.add(card);
		deck.add(card);
		
		card = new Card("heart", CardNumber.king);
		deck.add(card);
		deck.add(card);
		
		game.setDeck(deck);

		Setup.excute(game);
		Split.excute(game);

		assertEquals("PlayerTurn", Hit.excute(game));
	}
}
