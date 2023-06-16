package gameSystem;

public class Bet {
	private final int tip;
	private int refund;

	public Bet(int bet) {
		if(bet < 1 || bet > 10) {
			throw new IllegalArgumentException("不正な数値です");
		}
		this.tip = bet;
		this.refund = 0;
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
		for(Status key : player.getHand().keySet()) {
			if(player.getPoint(key).burstCheck()) {
				this.calc(Result.LOSE);
			} else if(dealer.getPoint(Status.PLAYING).burstCheck() || player.getPoint(key).getScore() > dealer.getPoint(Status.PLAYING).getScore()) {
				if(player.getHand().get(key).sizeCheck() && player.getPoint(key).bjCheck()) {
					this.calc(Result.NATURALBLACKJACK);			
				} else {
					this.calc(Result.WIN);
				}		
			} else if (player.getPoint(key).burstCheck() || player.getPoint(key).getScore() < dealer.getPoint(Status.PLAYING).getScore()) {
				this.calc(Result.LOSE);
			} else {
				this.calc(Result.DRAW);
			}			
		}
	}
}