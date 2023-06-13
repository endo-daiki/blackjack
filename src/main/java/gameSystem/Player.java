package gameSystem;

import java.util.HashMap;
import java.util.Map;

public class Player{
	private Map<String, Hand> hand = new HashMap<>();

	public Player() {
		hand.put("normal", new Hand());
	}
	
	public Map<String, Hand> getHand() {
		return this.hand;
	}
	public Point getPoint(String key) {
		return this.hand.get(key).getPoint();
	}
	public boolean splitCheck() {
		return (hand.size() == 1) && (hand.get("normal").splitCheck());
	}

	public void draw(Deck deck, String key) {
		this.hand.get(key).draw(deck);
	}
	public boolean movedCheck(String key) {
		return this.hand.get(key).movedCheck();
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
	public void isStand(String key) {
		this.hand.get(key).isStand();
	}	
	public void split() {
		Card card1 = this.hand.get("normal").getList().get(0);
		Card card2 = this.hand.get("normal").getList().get(1);
		
		this.hand.replace("normal", new Hand(card1));
		this.hand.put("split", new Hand(card2));
	}
}
