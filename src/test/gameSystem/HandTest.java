package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {
	static Hand hand;
	static Card card;
	
	@BeforeEach
	public void setup() {
		hand = new Hand();
		card = new Card(Suit.heart, CardNumber.one);
	}
	
	@Test
	public void testHand1() { //正しく初期化されているか
		assertNotNull(hand);
		assertEquals(false, hand.splitCheck()); //手札が0枚なので必ず、falseになる
	}
	
	@Test
	public void testHand2() {
		hand = new Hand(card);
		assertEquals(11, hand.getPoint().getScore());
	}
	
	@Test
	public void testDraw() { //正しくカードを引けているか
		hand.draw(card);
		
		List<Card> list = hand.getList();	
		assertEquals(1, list.size());
		assertEquals(11, hand.getPoint().getScore());
	}

	@Test
	public void movedCheck() {
		assertEquals(false, hand.movedCheck());

		hand.draw(card);
		assertEquals(false, hand.movedCheck());

		Card kingCard = new Card(Suit.heart, CardNumber.king);
		hand.draw(kingCard);
		hand.draw(kingCard);

		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void testGetList() { //リストを正しく取得できているか
		List<Card> list = hand.getList();	
		assertEquals(0, list.size());
	}

	@Test
	public void testGetPoint() {
		Point point = hand.getPoint();
		assertNotNull(point);
	}

	@Test
	public void testIsStand() {
		hand.isStand();
		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void testSplitCheck() { //スプリットのチェックができているか確認
		hand.draw(card);
		hand.draw(card);
		List<Card> list = hand.getList();	
		assertEquals(2, list.size()); //手札が2枚である
		assertEquals(true, hand.splitCheck());

		hand.draw(card); //手札が2枚ではなくなる
		assertEquals(false, hand.splitCheck());
	}

}