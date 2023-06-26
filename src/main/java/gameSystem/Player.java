package gameSystem;

import java.util.HashMap;
import java.util.Map;

public class Player{
	private Map<Status, Hand> hand = new HashMap<>();

	public Player() {
		hand.put(Status.PLAYING, new Hand());
	}

	public Map<Status, Hand> getHand() {
		return this.hand;
	}
	public Point getPoint(Status status) {
		return this.hand.get(status).getPoint();
	}
	public boolean splitCheck() {
		return (hand.size() == 1) && (hand.get(Status.PLAYING).splitCheck());
	}

	public void draw(Card card, Status status) {
		this.hand.get(status).draw(card);
	}
	public boolean movedCheckAll() {
		for(Hand hand :this.hand.values()) {
			if(!hand.movedCheck()) {
				return false;
			}
		}
		return true;
	}
	public boolean burstCheckAll() {
		for(Hand hand :this.hand.values()) {
			if(!hand.getPoint().burstCheck()) {
				return false;
			}
		}
		return true;
	}
	public void isStand(Status status) {
		this.hand.get(status).isStand();
	}	
	public void split() {
		Card card1 = this.hand.get(Status.PLAYING).getList().get(0);
		Card card2 = this.hand.get(Status.PLAYING).getList().get(1);

		this.hand.replace(Status.PLAYING, new Hand(card1));
		this.hand.put(Status.SPLIT, new Hand(card2));
	}

	public boolean test(String key) {
		return hand.get(Status.valueOf(key)).getList().size() == 3 && getHand().size() == 2;
	}
}
