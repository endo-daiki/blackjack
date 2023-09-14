package gameSystem;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private List<Card> list; //カード一覧
	private Point point; //点数
	private boolean isStand; //行動可能かどうか
	private Result result; //勝敗結果

	public Hand() {
		list = new ArrayList<Card>();
		point = new Point();
		isStand = false;
	}
	public Hand(Card card) {
		this();
		draw(card);
	}

	public void draw(Card card) {
		list.add(card);
		point.calc(card);
	}
	public boolean movedCheck() {
		return this.point.burstCheck() || this.point.bjCheck() || this.isStand;
	}
	public List<Card> getList() {
		return list;
	}
	public Point getPoint() {
		return this.point;
	}
	public void isStand() {
		this.isStand = true;
	}
	public boolean splitCheck() {
		if(!sizeCheck()) {
			return false;
		}
		int firstCard = list.get(0).cardNumber.getPoint();
		int secondCard = list.get(1).cardNumber.getPoint();

		return firstCard == secondCard;
	}
	public boolean sizeCheck() {
		return this.list.size() == 2;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Result getResult() {
		return this.result;
	}
}
