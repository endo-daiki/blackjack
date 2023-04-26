package gameSystem;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import model.Card;
import model.Game;

public class Blackjack {
	private List<Card> deck;
	private String[] suit = {"spade","heart","diamond","club"};
    private String[] no = {"1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    private Card draw() {
    	Card card = deck.get(0);
    	deck.remove(0);
    	
    	return card;
    }
	
	public void setup(Game game) {
		for(String suit : suit) {
			for(String no : no) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
		
		List<Card> hand = new ArrayList<Card>();
		hand.add(draw());
		hand.add(draw());
		
		game.setPlayerHand(hand);
	}
}
