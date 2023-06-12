package gameSystem;

public enum Result {
	NATURALBLACKJACK(2.5),
	WIN(2),
	LOSE(-1),
	DRAW(0);

	private double bet;
	
	public double toBet() {
		return this.bet;
	}
	Result(double bet) {
		this.bet = bet;
	}
}
