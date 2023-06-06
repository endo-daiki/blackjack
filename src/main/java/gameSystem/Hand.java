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
	public Card getFirstCard() {
		return this.list.get(0);
	}
	public Card getSecondCard() {
		return this.list.get(1);
	}
	public boolean splitCheck() {
		if(!sizeCheck()) {
			return false;
		}
		String firstCardNo = getFirstCard().cardNumber.getNo();
		String secondCardNo = getSecondCard().cardNumber.getNo();

		return firstCardNo.equals(secondCardNo);
	}
	public boolean sizeCheck() {
		return list.size() == 2;
	}
}
