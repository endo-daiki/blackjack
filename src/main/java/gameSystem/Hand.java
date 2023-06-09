package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> list; 
	
	public Hand() {
		list = new ArrayList<Card>();
	}
	
	public Card draw(Deck deck) {
		Card card = deck.pull();
		list.add(card);
		
		return card;
	}

	public List<Card> getList() {
		return list;
	}
	
	public boolean splitCheck() {
		if(list.size() != 2) {
			return false;
		}
		String firstCardNo = list.get(0).getCardNumber().getNo();
		String secondCardNo = list.get(1).getCardNumber().getNo();
		
		return firstCardNo.equals(secondCardNo);
	}
}
