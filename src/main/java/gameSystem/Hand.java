package gameSystem;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class Hand {
	private List<Card> hand; 
	boolean ace = false;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public Card draw(Deck deck) {
		ace = false;
		Card card = deck.Draw();
		hand.add(card);
		if(card.getNo() == "1") {
			ace = true;
		}
		
		return card;
	}
	
	public boolean aceCheck() {
		return ace;
	}
	
	public List<Card> getList() {
		return hand;
	}
	
	public boolean splitCheck() {
		return hand.get(0).no == hand.get(1).no;
	}
}
