package gameSystem;

import model.Card;

public class Point {
	int point;
	boolean ace;
	
	public Point(){
		point = 0;
	}
	
	void calc(Card card) {
		int point = 0;
    	
		if(card.no.equals("1")) {
			point += 11;
		} else if(card.no.equals("j") || card.no.equals("q") || card.no.equals("k") ) {
			point += 10;
		} else {
			point += Integer.parseInt(card.no);
		}

    	this.point += point;
	}
	
	boolean burstCheck() {
		if(this.point > 21) {
			return true;
		}
		return false;
	}
	
	boolean bjCheck() {
		if(this.point == 21) {
			return true;
		}
		return false;
	}
	
	public int getPoint() {
		return point;
	}
}
