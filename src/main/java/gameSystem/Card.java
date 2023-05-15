package gameSystem;

public class Card {
	public String suit;
	public String no;
	
	public Card(String suit, String no) {
		this.suit = suit;
		this.no = no;
	}
	
	public String getSuit() {
		return this.suit;
	}
	public String getNo() {
		return this.no;
	}
}
