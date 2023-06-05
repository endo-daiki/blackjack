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
		Card card = new Card("heart", CardNumber.one);
		deck.add(card);
		deck.add(card);
		
		game.setDeck(deck);

		Setup.excute(game);
		assertEquals("PlayerTurn", Split.excute(game));
	}

	@Test
	public void testBjSplit() {
		Card twoCard = new Card("heart", CardNumber.two);
		Card kingCard = new Card("heart", CardNumber.king);
		Card aceCard = new Card("heart", CardNumber.one);
		Deck deck = new Deck();

		deck.add(kingCard);
		deck.add(twoCard);
		deck.add(aceCard);
		deck.add(aceCard);
		
		game.setDeck(deck);

		Setup.excute(game);

		assertEquals("PlayerTurn", Split.excute(game));
	}
}
