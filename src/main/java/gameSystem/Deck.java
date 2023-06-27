package gameSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deck;
	private final Suit[] suit = Suit.values();
	private final CardNumber[] cardNumber = CardNumber.values();

	public Deck() {
		deck = new ArrayList<Card>();

		for(Suit suit : suit) {
			for(CardNumber no : cardNumber) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
		
		Card card1 = new Card(Suit.heart, CardNumber.king);
		Card card2 = new Card(Suit.diamond, CardNumber.ten);
		
		deck.remove(card1);
		deck.remove(card2);
		deck.add(0, card1);
		deck.add(0, card2);
	}

	public Card pull() {
		Card card = deck.get(0);
		deck.remove(0);

		return card;
	}
}
