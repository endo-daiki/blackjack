package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {
	static Hand hand;
	static Deck deck;
	
	@BeforeEach
	public void setup() {
		hand = new Hand();
		deck = new Deck();
	}
	
	@Test
	public void testHand() { //正しく初期化されているか
		assertNotNull(hand);
		assertEquals(false, hand.splitCheck()); //手札が0枚なので必ず、falseになる
	}
	
	@Test
	public void testDraw() { //正しくカードを引けているか
		hand.draw(deck);
		
		List<Card> list = hand.getList();	
		assertEquals(1, list.size());
	}
	
	@Test
	public void testGetList() { //リストを正しく取得できているか
		List<Card> list = hand.getList();	
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSplitCheck() { //スプリットのチェックができているか確認
		hand.draw(deck);
		hand.draw(deck);
		List<Card> list = hand.getList();	
		assertEquals(2, list.size()); //手札が2枚である

		hand.draw(deck); //手札が2枚ではなくなる
		assertEquals(false, hand.splitCheck());
	}

}