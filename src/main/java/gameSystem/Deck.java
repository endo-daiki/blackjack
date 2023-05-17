package gameSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;

public class Deck {
	private static List<Card> deck;
	private static final String[] suit = {"spade","heart","diamond","club"};
    private static final String[] no = {"1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    public Deck() {
    	deck = new ArrayList<Card>();
    	
    	for(String suit : suit) {
			for(String no : no) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}
    	
		Collections.shuffle(deck);
    }
        
    public Card Draw() {
    	Card card = deck.get(0);
    	deck.remove(0);
    	
    	return card;
    }
}
