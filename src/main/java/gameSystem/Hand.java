package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> list; 
	private Point point;
	private boolean isStand;

	public Hand() {
		list = new ArrayList<Card>();
		point = new Point();
		isStand = false;
	}
	public Hand(Card card) {
		list = new ArrayList<Card>();
		point = new Point();
		isStand = false;
		
		list.add(card);
		point.calc(card);
	}

	public Card draw(Deck deck) {
		Card card = deck.pull();
		list.add(card);
		point.calc(card);
		
		return card;
	}
	
	public boolean movedCheck() {
		return this.point.burstCheck() || this.point.bjCheck() || this.isStand;
	}

	public List<Card> getList() {
		return list;
	}
	public Point getPoint() {
		return this.point;
	}
	public void isStand() {
		this.isStand = true;
	}

	public boolean splitCheck() {
		if(sizeCheck() != 2) {
			return false;
		}
		String firstCardNo = list.get(0).cardNumber.getNo();
		String secondCardNo = list.get(1).cardNumber.getNo();

		return firstCardNo.equals(secondCardNo);
	}

	public int sizeCheck() {
		return list.size();
	}
}
