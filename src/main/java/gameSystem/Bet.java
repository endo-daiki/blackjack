package gameSystem;

public class Bet {
	private final int tip;
	private int refund;

	public Bet(int bet) {
		if(bet < 1 || bet > 10) {
			throw new IllegalArgumentException("不正な数値です");
		}
		this.tip = bet;
	}

	public void calc(Result result) {
		this.refund += this.tip * result.toBet();
	}

	public int getTip() {
		return this.tip;
	}

	public int refund() {
		return this.refund;
	}
	
	public void judge(Player player, Player dealer) {
//		if(playerPoint.burstCheck()) {
//			this.result = Result.LOSE;
//			return;
//		}
//		if(dealerPoint.burstCheck() || this.point.getScore() > dealerPoint.getScore()) {
//			if(sizeCheck() == 2 && this.point.bjCheck()) {
//				this.result = Result.NATURALBLACKJACK;			
//				return;
//			}
//			this.result = Result.WIN;
//		} else if (this.getPoint().burstCheck() || this.point.getScore() < dealerPoint.getScore()) {
//			this.result = Result.LOSE;
//		} else {
//			this.result = Result.DRAW;
//		}
	}
}