package gameSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;

class SplitTest {
	static Game game;
	static Card aceCard = new Card("heart", CardNumber.one);
	static Card nineCard = new Card("heart", CardNumber.nine);
	static Card kingCard = new Card("heart", CardNumber.king);
	
	@BeforeEach
	public void setup() {
		game = new Game(10);
	}
	
	@Test 
	public void testSplit() {
		Deck deck = new Deck() {
			@Override
			public Card pull() { //必ずaceを引くとする
				return aceCard;
			}
		};
		game.setDeck(deck);
		Setup.excute(game);
		assertEquals("PlayerTurn", Split.excute(game));
	}

	@Test
	public void testBjSplit() {
		
	}
}
