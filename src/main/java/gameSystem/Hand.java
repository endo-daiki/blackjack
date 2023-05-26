package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> hand; 
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public Card draw(Deck deck) {
		Card card = deck.Draw();
		hand.add(card);
		
		return card;
	}

	public List<Card> getList() {
		return hand;
	}
	
	public boolean splitCheck() {
		if(hand.size() != 2) {
			return false;
		}
		return hand.get(0).getNo().getPoint() == hand.get(1).getNo().getPoint();
	}
}
