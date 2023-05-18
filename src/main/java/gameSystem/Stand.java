package gameSystem;

import model.Game;

public class Stand {
	private static String url;
	
	Stand(Game game) {
		Point playerPoint = game.getPlayerPoint();
		if(playerPoint.burstCheck()) {
			game.setResult("lose");
			return;
		} 

		Deck deck = game.getDeck();
		Hand dealerHand = game.getDealerHand();
		Point dealerPoint = game.getDealerPoint();
		
		while(dealerPoint.point < 17) {
			dealerHand.draw(deck);
			dealerPoint.calc(dealerHand.draw(deck));
		}
		game.setDealerPoint(dealerPoint);
				
		if(dealerPoint.burstCheck() || playerPoint.point > dealerPoint.point) {
			game.setResult("win");
		} else if (playerPoint.burstCheck() || playerPoint.point < dealerPoint.point) {
			game.setResult("lose");
		} else {
			game.setResult("draw");
		}
		
		url = "Result";
	}
	
	public static String getUrl() {
		return url;
	}
}
