package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {
	static Hand hand;
	static Card aceCard;
	static Card kingCard;
	
	@BeforeEach
	public void setup() {
		hand = new Hand();
		aceCard = new Card(Suit.heart, CardNumber.one);
		kingCard = new Card(Suit.heart, CardNumber.king);
	}
	
	@Test
	public void testHand1() { //正しく初期化されているか
		assertNotNull(hand);
		assertEquals(false, hand.splitCheck()); //手札が0枚なので必ず、falseになる
		List<Card> list = hand.getList();
		assertEquals(0, list.size());
		assertEquals(0, hand.getPoint().getScore());
	}
	
	@Test
	public void testHand2() { 
		Hand hand = new Hand(aceCard);
		List<Card> list = hand.getList();
		assertEquals(1, list.size());
		assertEquals(11, hand.getPoint().getScore());
	}
	
	@Test
	public void testDraw() { //正しくカードを引けているか
		hand.draw(kingCard);
		
		List<Card> list = hand.getList();	
		assertEquals(1, list.size());
		assertEquals(10, hand.getPoint().getScore());
	}

	@Test
	public void movedCheckBurst() { //バーストの判定ができているか
		assertEquals(false, hand.movedCheck());

		hand.draw(kingCard);
		hand.draw(kingCard);
		assertEquals(false, hand.movedCheck());

		hand.draw(kingCard);
		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void movedChecBlackjack() { //ブラックジャック(21点)の判定ができているか
		hand.draw(kingCard);
		assertEquals(false, hand.movedCheck());

		hand.draw(aceCard);
		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void movedChecisStand() { //スタンドの判定ができているか
		hand.draw(kingCard);
		hand.draw(kingCard);
		assertEquals(false, hand.movedCheck());

		hand.isStand();
		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void testGetList() { //リストを正しく取得できているか
		List<Card> list = hand.getList();	
		assertEquals(0, list.size());

		hand.draw(aceCard);
		list = hand.getList();
		assertEquals(1, list.size());
	}

	@Test
	public void testGetPoint() {
		hand.draw(kingCard);

		Point point = hand.getPoint();
		assertEquals(10, point.getScore());
	}

	@Test
	public void testIsStand() {
		hand.isStand();
		assertEquals(true, hand.movedCheck());
	}
	
	@Test
	public void testSplitCheckFalse() { //スプリットのチェックができているか確認
		hand.draw(kingCard); //手札一枚なのでfalse
		assertEquals(false, hand.splitCheck());
		
		hand.draw(aceCard); //手札2枚だが同じでないのでfalse
		assertEquals(false, hand.splitCheck());
	}
	
	@Test
	public void testSplitCheckTrue() { //スプリットのチェックができているか確認
		hand.draw(aceCard); 
		hand.draw(aceCard);
		assertEquals(true, hand.splitCheck());
	}
	
	@Test
	public void testResult() {
		hand.setResult(Result.WIN);
		assertEquals(Result.WIN, hand.getResult()); //正しくsetされている、getできることが両方チェックできる
	}
}