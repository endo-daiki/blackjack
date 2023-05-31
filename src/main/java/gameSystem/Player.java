package gameSystem;

public class Player{
	private Hand hand;
	private Point point;
	public String result;
	
	public Player() {
		hand = new Hand();
		point = new Point();
		this.result = "ready";
	}
	
	public void draw(Deck deck) {
		Card card = this.hand.draw(deck);
		point.calc(card);
	}
	public Hand getHand() {
		return hand;
	}
	public Point getPoint() {
		return point;
	}
	public String getResult() {
		return this.result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}		
	public void judge(Player dealer) {
		if(this.result.equals("ready")) {
			return;
		}
		if(this.point.burstCheck()) {
			this.result = "lose";
			return;
		}
		int playerScore = this.point.getScore();
		int dealerScore = dealer.getPoint().getScore();
		
		this.result = "draw";
		if(dealer.getPoint().burstCheck() || playerScore > dealerScore) {
			this.result = "win";
		} else if (this.getPoint().burstCheck() || playerScore < dealerScore) {
			this.result = "lose";
		}
	}
}
