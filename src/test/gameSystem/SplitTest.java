package gameSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;

class SplitTest {
	static Game game;
	static Card aceCard = new Card(Suit.heart, CardNumber.one);
	static Card nineCard = new Card(Suit.heart, CardNumber.nine);
	static Card kingCard = new Card(Suit.heart, CardNumber.king);
	
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
		Player player = new Player();
		player.draw(aceCard, Status.PLAYING);
		player.draw(aceCard, Status.PLAYING);

		Deck deck = new Deck() {
			@Override
			public Card pull() {
				return kingCard;
			}
		};

		game.setDeck(deck);
		game.setPlayer(player);
		assertEquals("Result", Split.excute(game));
	}

	@Test
	public void testPlayingBjSplit() {
		Player player = new Player();
		player.draw(aceCard, Status.PLAYING);
		player.draw(aceCard, Status.PLAYING);

		Deck deck = new Deck() {
			int i = 2;
			@Override
			public Card pull() { 
				if(i % 2 == 0) {
					i++;
					return kingCard;
				}
				i++;
				return nineCard;
			}
		};

		game.setDeck(deck);
		game.setPlayer(player);
		assertEquals("PlayerTurn", Split.excute(game));
	}
}
