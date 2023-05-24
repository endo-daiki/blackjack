package gameSystem;

import model.Card;

public class Player{
	private Hand hand;
	private Point point;
	
	public Player() {
		hand = new Hand();
		point = new Point();
	}
	
	public void Draw(Deck deck) {
		Card card = this.hand.draw(deck);
		point.calc(card);
	}
	public Hand getHand() {
		return hand;
	}
	public Point getPoint() {
		return point;
	}
	
//	public void split(Player spliter) {
//		spliter.hand = this.hand;
//		spliter.hand.getList().remove(0);
//		this.hand.getList().remove(1);
//	}
}
