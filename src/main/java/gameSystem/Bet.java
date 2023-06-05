package gameSystem;

public class Bet {
	final private int tip;
	private int refund;
	
	public Bet(int bet) {
		this.tip = bet;
	}
	
	public void calc(String result) {
		switch(result) {
			case "naturalwin" :
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