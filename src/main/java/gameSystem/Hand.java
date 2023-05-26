package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> hand; 
	private int counter;
	boolean ace = false;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public Card draw(Deck deck) {
		ace = false;
		Card card = deck.Draw();
		hand.add(card);
		counter++;
		if(card.getNo().getPoint() == 1) {
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
		return hand.get(0).no == hand.get(1).no && counter == 2;
	}
}
