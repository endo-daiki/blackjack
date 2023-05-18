package gameSystem;

import model.Game;

public class Hit {
	private static String url;
	
	Hit(Game game) {
		Deck deck = game.getDeck();
		Hand playerHand = game.getPlayerHand();
    	Point playerPoint = game.getPlayerPoint();
    	
    	playerPoint.calc(playerHand.draw(deck));
    	
    	game.setDeck(deck);
    	game.setPlayerHand(playerHand);
    	game.setPlayerPoint(playerPoint);
		
    	if(playerPoint.burstCheck() || playerPoint.bjCheck()) {
    		new Stand(game);
			url = Stand.getUrl();
		}
    	
		url = "PlayerTurn";
	}
	
	public static String getUrl() {
		return url;
	}
}
