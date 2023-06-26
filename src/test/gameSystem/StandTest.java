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
		Deck deck = new Deck() { //必ずsetupで自動standしないようにする
			@Override
			public Card pull() { 
				return new Card(Suit.heart, CardNumber.one);
			}
		};
		game.setDeck(deck);
		Setup.excute(game);
		
		assertEquals("Result", Stand.excute(game, "PLAYING")); //自分でstandを選択し終了
	}

	@Test
	public void testSplitStand() {
		Deck deck = new Deck() {
			@Override
			public Card pull() { 
				return new Card(Suit.heart, CardNumber.one);
			}
		};
		game.setDeck(deck);

		Setup.excute(game);
		Split.excute(game);

		assertEquals("PlayerTurn", Stand.excute(game, "PLAYING")); //自分でstandを選択し終了(スプリット手札がまだ動けるのでplayerTurnに戻る)
		assertEquals("Result", Stand.excute(game, "SPLIT")); //自分でstandを選択し終了
	}
}
