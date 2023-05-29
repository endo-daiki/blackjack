package gameSystem;

public class Card {
	public String suit;
	public CardNumber cardNumber;
	
	public Card(String suit, CardNumber cardNumber) {
		this.suit = suit;
		this.cardNumber = cardNumber;
	}
	
	public String getSuit() {
		return this.suit;
	}
	public CardNumber getCardNumber() {
		return this.cardNumber;
	}
	
	public boolean courtCheck() {
		return this.cardNumber.getNo() == "j" || this.cardNumber.getNo() == "q" || this.cardNumber.getNo() == "k"; 
	}
}

