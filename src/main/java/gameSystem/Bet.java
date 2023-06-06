package gameSystem;

public class Bet {
	private final int tip;
	private int refund;

	public Bet(int bet) {
		if(10 < bet || 1 > bet) {
			throw new IllegalArgumentException("不正な数値です");
		}
		this.tip = bet;
	}

	public void calc(String result) {
		switch(result) {
		case "natural Blackjack" :
			refund += tip * 2.5;
			break;
		case "win" :
			refund += tip * 2;
			break;
		case "lose" :
			refund += tip * -1;
			break;
		default:
			refund += 0;
			break;
		}
	}

	public int getTip() {
		return this.tip;
	}

	public int refund() {
		return this.refund;
	}
}