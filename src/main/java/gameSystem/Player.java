package gameSystem;

public class Player{
	private Hand hand;
	private Hand splitHand;
	private Status status;

	public Player() {
		hand = new Hand();
		splitHand = new Hand();
		status = Status.PLAYING;
	}

	public void draw(Deck deck) {
		if(this.status == Status.SPLIT) {
			this.splitHand.draw(deck);
			return;
		}
		this.hand.draw(deck);
	}
	public boolean moveCheck() {
		if(this.status == Status.SPLIT) {
			return this.splitHand.moveCheck();
		}
		return this.hand.moveCheck();
	}
	public void isStand() {
		if(this.status == Status.SPLIT) {
			this.splitHand.isStand();
			this.status = Status.PLAYING;
			return;
		}
		this.hand.isStand();
		this.status = Status.SPLIT;
	}
	public void splitDraw(Deck deck) {
		this.splitHand.draw(deck);
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
}
