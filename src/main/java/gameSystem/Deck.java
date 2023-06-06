package gameSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deck;
	private final String[] suit = {"spade","heart","diamond","club"};
	private final CardNumber[] cardNumber = CardNumber.values();

	public Deck() {
		deck = new ArrayList<Card>();

		for(String suit : suit) {
			for(CardNumber no : cardNumber) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}

		Collections.shuffle(deck);
	}

	public Card pull() {
		Card card = deck.get(0);
		deck.remove(0);

		return card;
	}

	public void add(Card card) {
		deck.add(0, card);
	}
}
