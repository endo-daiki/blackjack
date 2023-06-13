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
		for(String key : player.getHand().keySet()) {
			if(player.getPoint(key).burstCheck()) {
				this.calc(Result.LOSE);
			} else if(dealer.getPoint("normal").burstCheck() || player.getPoint(key).getScore() > dealer.getPoint("normal").getScore()) {
				if(player.getHand().get(key).getList().size() == 2 && player.getPoint(key).bjCheck()) {
					this.calc(Result.NATURALBLACKJACK);			
				} else {
					this.calc(Result.WIN);
				}		
			} else if (player.getPoint(key).burstCheck() || player.getPoint(key).getScore() < dealer.getPoint("normal").getScore()) {
				this.calc(Result.LOSE);
			} else {
				this.calc(Result.DRAW);
			}			
		}
	}
}