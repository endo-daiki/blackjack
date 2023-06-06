package gameSystem;

public class Point {
	int score;
	public int aceCount;

	public Point(){
		score = 0;
	}

	void calc(Card card) {
		int addPoint = card.cardNumber.getPoint();
		if(addPoint == 11) {
			aceCount++;
		}
		this.score += addPoint;

		if(this.score > 21 && aceCount > 0) {
			while(this.score > 21) {
				this.score -= 10;
				aceCount--;
			}
		}
	}
	public boolean burstCheck() {
		return this.score > 21 && this.score > 0;
	}
	public boolean bjCheck() {
		return this.score == 21;
	}
	public int getScore() {
		return score;
	}
	public boolean aceCountCheck() {
		return aceCount > 0;
	}
}
