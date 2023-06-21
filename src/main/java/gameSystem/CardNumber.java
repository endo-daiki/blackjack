package gameSystem;

public enum CardNumber {
	one(11),
	two(2),
	three(3),
	four(4),
	five(5),
	six(6),
	seven(7),
	eight(8),
	nine(9),
	ten(10),
	jack(10),
	queen(10),
	king(10);

	private int point;

	public int getPoint() {
		return point;
	}

	CardNumber(int point) {
		this.point = point;
	}
}
