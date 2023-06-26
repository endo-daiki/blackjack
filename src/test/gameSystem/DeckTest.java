package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {
	static Deck deck;
	static List<Card> list = new ArrayList<Card>();
	
	@BeforeAll
	public static void setup() {
		for(Suit suit : Suit.values()) { //デッキのlistと同じ
			for(CardNumber no : CardNumber.values()) {
				Card card = new Card(suit, no);
				list.add(card);
			}
		}
	}
	
	@BeforeEach
	public void deckSet() {
		deck = new Deck();
	}

	@Test
	public void testDeck() { //正しく初期化されているか確認,デッキ内容52枚も確認する
		assertNotNull(deck);
		
		for(int i = 0; i < list.size(); i++) {
			Card card = deck.pull();
			if(Arrays.asList(list).contains(card)) {
	            fail("存在しないカードです");
	        }
		}
	}
	
	@Test
	public void testPull() { //正しくカードが引かれているか確認
//		Deck deck = new Deck();
		Card card = deck.pull();
		
		assertNotNull(card);
		if(Arrays.asList(list).contains(card)) {
            fail("存在しないカードです");
        }
	}
}
