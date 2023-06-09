package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> list; 
	private Point point;
	private Result result;

	public Hand() {
		list = new ArrayList<Card>();
		point = new Point();
		result = Result.READY;
	}

	public Card draw(Deck deck) {
		Card card = deck.pull();
		list.add(card);
		point.calc(card);
		
		return card;
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

	public boolean splitCheck() {
		if(!sizeCheck()) {
			return false;
		}
		String firstCardNo = list.get(0).cardNumber.getNo();
		String secondCardNo = list.get(1).cardNumber.getNo();

		return firstCardNo.equals(secondCardNo);
	}

	public boolean sizeCheck() {
		return list.size() == 2;
	}
	
	public void judge(Point dealerPoint) {
		if(this.point.burstCheck()) {
			this.result = Result.LOSE;
			return;
		}
		if(dealerPoint.burstCheck() || this.point.getScore() > dealerPoint.getScore()) {
			if(sizeCheck() && this.point.bjCheck()) {
				this.result = Result.NATURALWIN;			
			}
			this.result = Result.WIN;
		} else if (this.getPoint().burstCheck() || this.point.getScore() > dealerPoint.getScore()) {
			this.result = Result.LOSE;
		} else {
			this.result = Result.DRAW;
		}
	}
}
