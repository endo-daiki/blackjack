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
		Card card = new Card("heart", CardNumber.two);
		
		deck.add(card);
		deck.add(card);
		game.setDeck(deck);
		
		Setup.excute(game);
		assertEquals("PlayerTurn", Hit.excute(game));
	}	

	@Test
	public void testBurstHit() {
		game = new Game(10);
		Deck deck = new Deck();
		Card card = new Card("heart", CardNumber.ten);
		
		deck.add(card);
		deck.add(card);
		
		game.setDeck(deck);
		
		Setup.excute(game);
		assertEquals("Result", Hit.excute(game));
	}	

	@Test
	public void testSplitHit() {
		game = new Game(10);
		Deck deck = new Deck();
		Card twoCard = new Card("heart", CardNumber.two);
		Card threeCard = new Card("heart", CardNumber.three);
		
		deck.add(threeCard);
		deck.add(threeCard);
		deck.add(twoCard);
		deck.add(twoCard);
		deck.add(twoCard);
		deck.add(twoCard);
		
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game));
		assertEquals("split", game.getPlayer().getResult());
	}

	@Test
	public void testSplitBurstHit() {
		game = new Game(10);
		Deck deck = new Deck();
		Card card = new Card("heart", CardNumber.ten);
		
		deck.add(card);
		deck.add(card);
		deck.add(card);
		deck.add(card);
		deck.add(card);
		deck.add(card);
		
		game.setDeck(deck);
		
		Setup.excute(game);
		Split.excute(game);
		
		assertEquals("PlayerTurn", Hit.excute(game));
		assertEquals("stand", game.getSplit().getResult());
	}
}
