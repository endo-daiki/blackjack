package gameSystem;

public enum CardNumber {
	one(11, "1"),
	two(2, "2"),
	three(3, "3"),
	four(4, "4"),
	five(5, "5"),
	six(6, "6"),
	seven(7, "7"),
	eight(8, "8"),
	nine(9, "9"),
	ten(10, "10"),
	jack(10, "j"),
	queen(10, "q"),
	king(10, "k");
	
	private int point;
	private String no;
	
	public int getPoint() {
		return point;
	}
	
	public String getNo() {
		return no;
	}
	
	CardNumber(int point, String no) {
		this.point = point;
		this.no = no;
	}
}
