package gameSystem;

import java.util.HashMap;
import java.util.Map;

public class Player{
	private Hand hand;
	private Hand splitHand;
	private Status status;
	private boolean playHandisNormal;
	
	private Map<String, Hand> test = new HashMap<>();

	public Player() {
		hand = new Hand();
		splitHand = new Hand();
		status = Status.PLAYING;
		playHandisNormal = true;
		
		test.put("normal", hand);
	}
	
	public Map<String, Hand> getHandList() {
		return this.test;
	}

	public void draw(Deck deck) {
//		if(playHandisNormal) {
//			this.test.get("normal").draw(deck);
//			return;
//		}
//		this.test.get("split").draw(deck);
		
		if(this.status == Status.SPLIT) {
			this.splitHand.draw(deck);
			return;
		}
		this.hand.draw(deck);
	}
	public boolean moveCheck() {
//		if(playHandisNormal) {
//			this.test.get("normal").moveCheck();
//			return;
//		}
//		this.test.get("split").moveCheck();
		
		if(this.status == Status.SPLIT) {
			return this.splitHand.moveCheck();
		}
		return this.hand.moveCheck();
	}
	public void isStand() {
//		if(playHandisNormal) {
//			this.test.get("normal").setStatus(Status.Stand);
//			return;
//		}
//		this.test.get("split").setStatus(Status.Stand);
		
		if(this.status == Status.SPLIT) {
			this.splitHand.isStand();
			this.status = Status.PLAYING;
			return;
		}
		this.hand.isStand();
		this.status = Status.SPLIT;
	}

	public Hand getHand() {
		return hand;
	}
	public Hand getSplitHand() {
		return splitHand;
	}
	public Point getPoint() {
		return this.hand.getPoint();
	}
	public Point getSplitPoint() {
		return this.splitHand.getPoint();
	}
	public Status getStatus() {
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}		
	public void judge(Player dealer) {
		Point dealerPoint = dealer.getPoint();
		hand.judge(dealerPoint);
		splitHand.judge(dealerPoint);
	}
	public void split() {
		this.test.put("split", splitHand);
	}
}
