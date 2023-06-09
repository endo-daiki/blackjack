package gameSystem;

public class Player{
	private Hand hand;
	private Hand splitHand;
	private Result result;

	public Player() {
		hand = new Hand();
		splitHand = new Hand();
		this.result = Result.READY;
	}

	public void draw(Deck deck) {
		if(this.result == Result.SPLIT) {
			this.splitHand.draw(deck);
			return;
		}
		this.hand.draw(deck);
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
	public Result getResult() {
		return this.result;
	}
	public void setResult(Result result) {
		this.result = result;
	}		
	public void judge(Player dealer) {
		Point dealerPoint = dealer.getPoint();
		hand.judge(dealerPoint);
		splitHand.judge(dealerPoint);
	}
}
