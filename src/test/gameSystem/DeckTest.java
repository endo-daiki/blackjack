package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeckTest {
	static Deck deck = new Deck();

	@Test
	public void testDeckInstanse() { //正しく初期化されているか確認
		assertNotNull(deck);
	}
	
	@Test
	public void testPull() { //正しくカードが引かれているか確認
		Card card = deck.pull();
		
		assertNotNull(card);
	}

	@Test
	public void testAdd() { //正しくデッキにカードが追加されているか確認
		Card createCard = new Card("heart", CardNumber.one);
		deck.add(createCard); //カードをデッキに追加

		Card drawCard = deck.pull(); //追加したカードをドロー
		assertEquals(createCard, drawCard); //作ったカードと引いたカードが同じである
	}

}
