package gameSystem;

public class Card {
	public String suit;
	public CardNumber no;
	
	public Card(String suit, CardNumber no) {
		this.suit = suit;
		this.no = no;
	}
	
	public String getSuit() {
		return this.suit;
	}
	public CardNumber getNo() {
		return this.no;
	}
	
	public boolean courtCheck() {
		return this.no.getNo() == "j" || this.no.getNo() == "q" || this.no.getNo() == "k"; 
	}
}

