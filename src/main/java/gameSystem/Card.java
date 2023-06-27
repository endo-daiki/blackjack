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
}