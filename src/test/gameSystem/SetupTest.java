package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Game;

class SetupTest {	
	static Game game;
	static Card aceCard = new Card(Suit.heart, CardNumber.one);
	static Card nineCard = new Card(Suit.heart, CardNumber.nine);
	static Card kingCard = new Card(Suit.heart, CardNumber.king);

	@Test 
	public void testSetup() {
		game = new Game(10);
		Deck deck = new Deck() {
			@Override
			public Card pull() { //デッキからaceを2枚引いてplayerTurnに戻る
				return aceCard;
			}
		};
		game.setDeck(deck);

		assertEquals("PlayerTurn", Setup.excute(game));
	}

	@Test
	public void testBjSetup() { //手札がbjになったので、stand処理を行う
		game = new Game(10);
		Deck deck = new Deck() {
			int i = 0;
			@Override
			public Card pull() { 
				if(i % 2 == 0) {
					i++;
					return kingCard;
				}
				i++;
				return aceCard;
			}
		};
		game.setDeck(deck);

		assertEquals("Result", Setup.excute(game));
	}
}
