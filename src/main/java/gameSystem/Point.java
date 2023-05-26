package gameSystem;

public class Point {
	int point;
	public int aceCount;
	
	public Point(){
		point = 0;
	}
	
	void calc(Card card) {
		int addPoint = card.getNo().getPoint();
		if(addPoint == 11) {
			aceCount++;
		}
    	this.point += card.getNo().getPoint();
    	
    	if(this.point > 21 && aceCount > 0) {
    		while(this.point > 21) {
    			this.point -= 10;
    			aceCount--;
    		}
    	}
	}
	
	boolean burstCheck() {
		return this.point > 21;
	}
	
	boolean bjCheck() {
		return this.point == 21;
	}
	
	public int getPoint() {
		return point;
	}
	public boolean aceCount() {
		return aceCount > 0;
	}
}
