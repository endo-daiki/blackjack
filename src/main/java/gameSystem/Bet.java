package gameSystem;

public class Bet {
	final private int bet;
	private int refund;
	
	public Bet(int bet) {
		this.bet = bet;
	}
	
	public void calc(String result) {
		switch(result) {
			case "naturalwin" :
				refund += bet * 2.5;
				break;
			case "win" :
				refund += bet * 2;
				break;
			case "lose" :
				refund += bet * -1;
				break;
			default:
				refund += 0;
				break;
		}
	}
	
	public int getBet() {
		return this.bet;
	}
	
	public int refund() {
		return this.refund;
	}
}