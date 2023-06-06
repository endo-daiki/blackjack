package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	static Player player;
	
	@BeforeEach
	public void setup() {
		player = new Player();
	}
	
	@Test
	public void testPlayer() {
		assertNotNull(player.getHand());
		assertNotNull(player.getPoint());
		assertEquals("ready", player.getResult());
	}
	
	@Test
	public void testDraw() {
		Card card = new Card("heart", CardNumber.one);
		Deck deck = new Deck();
		deck.add(card);

		player.draw(deck);
		
		Hand hand = player.getHand();
		assertEquals(card, hand.getList().get(0));

		Point point = player.getPoint();
		assertEquals(11, point.getScore());
	}
	
	@Test
	public void testGetHand() {
		Hand hand = player.getHand();
		assertNotNull(hand);
	}
	
	@Test
	public void testGetPoint() {
		Point point = player.getPoint();
		assertNotNull(point);
	}

	@Test
	public void testGetResult() {
		assertEquals("ready", player.getResult());
	}

	@Test
	public void testSetResult() {
		player.setResult("win");
		assertEquals("win", player.getResult());
	}

	@Test
	public void testReadyJudge() {
		Player dealer = new Player();
		
		player.judge(dealer);
		assertEquals("ready", player.getResult());
	}
	
	@Test
	public void testBurstJudge() {
		Player dealer = new Player();
		Deck deck = new Deck();
		
		Card card = new Card("heart", CardNumber.king);
		deck.add(card);
		deck.add(card);
		deck.add(card);
		
		player.setResult("playing");

		player.draw(deck);
		player.draw(deck);
		player.draw(deck);

		player.judge(dealer);
		assertEquals("lose", player.getResult());
	}
	
	@Test
	public void testNaturalWinJudge() {
		Player dealer = new Player();
		Deck deck = new Deck();
		
		Card kingCard = new Card("heart", CardNumber.king);
		Card aceCard = new Card("heart", CardNumber.one);
		deck.add(kingCard);
		deck.add(aceCard);

		player.setResult("playing");

		player.draw(deck);
		player.draw(deck);
		
		dealer.draw(deck);

		player.judge(dealer);
		assertEquals("natural Blackjack", player.getResult());
	}
	
	@Test
	public void testWinJudge() {
		Player dealer = new Player();
		Deck deck = new Deck();
		
		Card kingCard = new Card("heart", CardNumber.king);
		Card aceCard = new Card("heart", CardNumber.one);
		deck.add(kingCard);
		deck.add(aceCard);

		player.setResult("playing");

		player.draw(deck);
		dealer.draw(deck);

		player.judge(dealer);
		assertEquals("win", player.getResult());
	}
	
	@Test
	public void testLoseJudge() {
		Player dealer = new Player();
		Deck deck = new Deck();
		
		Card kingCard = new Card("heart", CardNumber.king);
		Card aceCard = new Card("heart", CardNumber.one);
		deck.add(kingCard);
		deck.add(aceCard);

		player.setResult("playing");

		dealer.draw(deck);
		player.draw(deck);

		player.judge(dealer);
		assertEquals("lose", player.getResult());
	}
	
	@Test
	public void testDrawJudge() {
		Player dealer = new Player();
		Deck deck = new Deck();
		
		Card kingCard = new Card("heart", CardNumber.king);
		deck.add(kingCard);
		deck.add(kingCard);

		player.setResult("playing");

		dealer.draw(deck);
		player.draw(deck);

		player.judge(dealer);
		assertEquals("draw", player.getResult());
	}
}
