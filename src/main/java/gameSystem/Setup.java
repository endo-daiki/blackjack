package gameSystem;

import model.Game;

public class Setup {
	private static String url;
	
	Setup(Game game) {
		Deck deck = game.getDeck();
    	Hand hand = game.getPlayerHand();
    	Point point = game.getPlayerPoint();
    	
    	point.calc(hand.draw(deck));
    	point.calc(hand.draw(deck));
    	game.setPlayerHand(hand);
    	game.setPlayerPoint(point);
    	 	
    	hand = game.getDealerHand();
    	point = game.getDealerPoint();
    	
    	point.calc(hand.draw(deck));
    	point.calc(hand.draw(deck));
    	game.setDealerHand(hand);
    	game.setDealerPoint(point);
    	
    	game.setDeck(deck);
    	game.setResult("playing");
    	
    	if(game.getPlayerPoint().bjCheck()) {
			new Stand(game);
			url = Stand.getUrl();
		}
		
		url = "PlayerTurn";
	}
	
	static String getUrl() {
		return url;
	}
}
