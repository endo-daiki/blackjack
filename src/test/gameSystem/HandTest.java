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
	public void testDraw() {
		hand.draw(deck);
		List<Card> list = hand.getList();
			
		assertEquals(1, list.size());
	}
	
	@Test
	public void testGetList() {
		List<Card> list = hand.getList();	
		assertEquals(0, list.size());

		hand.draw(deck);
		list = hand.getList();
		assertEquals(1, list.size());
	}
	
	@Test
	public void testSplitCheck() { //スプリットのチェックができているか確認
		Card card = new Card("heart", CardNumber.one);
		deck.add(card); //デッキの上2枚を同じカードにする
		deck.add(card);

		hand.draw(deck);
		hand.draw(deck);
		assertEquals(true, hand.splitCheck()); //手札が2枚かつカードの数字が同じである

		hand.draw(deck); //手札が2枚ではなくなる
		assertEquals(false, hand.splitCheck());
	}

}