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
		for(Status key : player.getHand().keySet()) {
			if(player.getPoint(key).burstCheck()) {
				this.calc(Result.LOSE);
				player.getHand().get(key).setResult(Result.LOSE);
			} else 
			if(player.getPoint(key).getScore() > dealer.getPoint(Status.PLAYING).getScore() || dealer.getPoint(Status.PLAYING).burstCheck()) {
				if(player.getHand().get(key).sizeCheck() && player.getPoint(key).bjCheck()) {
					this.calc(Result.NATURALBLACKJACK);		
					player.getHand().get(key).setResult(Result.NATURALBLACKJACK);
				} else {
					this.calc(Result.WIN);
					player.getHand().get(key).setResult(Result.WIN);
				}		
			} else 
			if (player.getPoint(key).getScore() < dealer.getPoint(Status.PLAYING).getScore()) {
				this.calc(Result.LOSE);
				player.getHand().get(key).setResult(Result.LOSE);
			} else {
				this.calc(Result.DRAW);		
				player.getHand().get(key).setResult(Result.DRAW);
			}		
		}
	}
}