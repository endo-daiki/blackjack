package gameSystem;

import model.Card;

public class Point {
	int point;
	public int aceCount;
	
	public Point(){
		point = 0;
	}
	
	void calc(Card card) {
		int point = 0;
    	
		if(card.no.equals("1")) {
			point += 11;
			aceCount++;
		} else if(card.no.equals("j") || card.no.equals("q") || card.no.equals("k") ) {
			point += 10;
		} else {
			point += Integer.parseInt(card.no);
		}

    	this.point += point;
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
