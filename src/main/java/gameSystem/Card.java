package gameSystem;

public class Card {
	public final Suit suit;
	public final CardNumber cardNumber;

	public Card(Suit suit, CardNumber cardNumber) {
		this.suit = suit;
		this.cardNumber = cardNumber;
	}

	public boolean courtCheck() {
		return this.cardNumber == CardNumber.jack || this.cardNumber == CardNumber.queen || this.cardNumber == CardNumber.king; 
	}
	
	public boolean pointCheck(Card card) {
		return this.cardNumber.getPoint() == card.cardNumber.getPoint();
	}
	
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null) return false;
		if(!(o instanceof Card)) return false;
		Card card = (Card)o;
		if(this.cardNumber == card.cardNumber && this.suit == card.suit) {
			return true;
		}
		return false;
	}
}