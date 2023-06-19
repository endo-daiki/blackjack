package gameSystem;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class HitTest {
	static Game game;
	static Card aceCard = new Card("heart", CardNumber.one);
	static Card nineCard = new Card("heart", CardNumber.nine);
	static Card kingCard = new Card("heart", CardNumber.king);
	
	@Test
	public void testHit() {
		game = new Game(10);
		Deck deck = new Deck() {
			@Override
			public Card pull() { //必ずaceを引くとする
				return aceCard;
			}
		};
		game.setDeck(deck);
		assertEquals("PlayerTurn", Hit.excute(game, "PLAYING"));
	}	

	@Test
	public void testBurstHit() {
		game = new Game(10);
		Deck deck = new Deck() {
			@Override
			public Card pull() {
				return kingCard;
			}
		};
		game.setDeck(deck);
		
		Setup.excute(game);
		assertEquals("Result", Hit.excute(game, "PLAYING"));
	}	

	@Test
	public void testSplitHit() {
		game = new Game(10);
		Deck deck = new Deck() {
			@Override
			public Card pull() {
				return aceCard;
			}
		};
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game, "PLAYING"));
	}

	@Test
	public void testSplitBurstHit() {
		game = new Game(10);
		Deck deck = new Deck() {
			@Override
			public Card pull() {
				return kingCard;
			}
		};
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game, "SPLIT"));
	}
}
