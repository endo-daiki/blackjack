package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> list; 
	private Point point;
	private Result result;
	private boolean isStand;

	public Hand() {
		list = new ArrayList<Card>();
		point = new Point();
		isStand = false;
	}

	public Card draw(Deck deck) {
		Card card = deck.pull();
		list.add(card);
		point.calc(card);
		
		return card;
	}
	public boolean moveCheck() { //行動できるかどうか
		return this.point.burstCheck() || this.point.bjCheck() || this.isStand;
	}

	public List<Card> getList() {
		return list;
	}
	public Point getPoint() {
		return this.point;
	}
	public Result getResult() {
		return this.result;
	}
	public void isStand() {
		this.isStand = true;
	}
	
	public boolean splitCheck() {
		if(sizeCheck() != 2) {
			return false;
		}
		String firstCardNo = list.get(0).cardNumber.getNo();
		String secondCardNo = list.get(1).cardNumber.getNo();

		return firstCardNo.equals(secondCardNo);
	}

	public int sizeCheck() {
		return list.size();
	}
	
	public void judge(Point dealerPoint) {
		if(sizeCheck() == 0) {
			this.result = Result.DRAW;
			return;
		}
		if(this.point.burstCheck()) {
			this.result = Result.LOSE;
			return;
		}
		if(dealerPoint.burstCheck() || this.point.getScore() > dealerPoint.getScore()) {
			if(sizeCheck() == 2 && this.point.bjCheck()) {
				this.result = Result.NATURALWIN;			
			}
			this.result = Result.WIN;
		} else if (this.getPoint().burstCheck() || this.point.getScore() < dealerPoint.getScore()) {
			this.result = Result.LOSE;
		} else {
			this.result = Result.DRAW;
		}
	}
}
