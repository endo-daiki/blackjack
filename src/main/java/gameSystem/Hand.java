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

		draw(card);
	}

	public void draw(Card card) {
		list.add(card);
		point.calc(card);
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
		if(!sizeCheck()) {
			return false;
		}
		Card firstCard = list.get(0);
		Card secondCard = list.get(1);

		return firstCard.equalCheck(secondCard);
	}
	public boolean sizeCheck() {
		return this.list.size() == 2;
	}
}
