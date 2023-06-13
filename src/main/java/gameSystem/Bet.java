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

	private void calc(Result result) {
		this.refund += this.tip * result.toBet();
	}

	public int getTip() {
		return this.tip;
	}

	public int refund() {
		return this.refund;
	}
	
	public void judge(Player player, Player dealer) {
		for(Hand hand : player.getHand().values()) {
			if(player.getPoint().burstCheck()) {
				this.calc(Result.LOSE);
			} else if(dealer.getPoint().burstCheck() || player.getPoint().getScore() > dealer.getPoint().getScore()) {
				if(hand.sizeCheck() == 2 && player.getPoint().bjCheck()) {
					this.calc(Result.NATURALBLACKJACK);			
				} else {
					this.calc(Result.WIN);
				}		
			} else if (player.getPoint().burstCheck() || player.getPoint().getScore() < dealer.getPoint().getScore()) {
				this.calc(Result.LOSE);
			} else {
				this.calc(Result.DRAW);
			}			
		}
	}
}