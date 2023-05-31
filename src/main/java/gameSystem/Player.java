package gameSystem;

public class Player{
	private Hand hand;
	private Point point;
	
	public Player() {
		hand = new Hand();
		point = new Point();
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
}
