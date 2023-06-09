package gameSystem;

public enum Result {
	NATURALWIN(2.5),
	WIN(2),
	LOSE(-1),
	DRAW(0),
	
	READY(0),
	PLAYING(0),
	SPLIT(0),
	STAND(0);
	
	private double bet;
	
	public double toBet() {
		return this.bet;
	}
	Result(double bet) {
		this.bet = bet;
	}
}
