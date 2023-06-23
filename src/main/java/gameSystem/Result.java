package gameSystem;

public enum Result {
	NATURALBLACKJACK(2.5, "Natural BlackJack!!"),
	WIN(2, "You Win!!"),
	LOSE(-1, "You Lose..."),
	DRAW(0, "Draw...");

	private double bet;
	private String msg;

	public double toBet() {
		return this.bet;
	}
	public String getMsg() {
		return this.msg;
	}
	Result(double bet, String msg) {
		this.bet = bet;
		this.msg = msg;
	}
}
