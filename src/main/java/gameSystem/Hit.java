package gameSystem;

import model.Game;

public class Hit {
	private static String url;
	
	Hit(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
    	
    	player.draw(deck);
    	
    	game.setDeck(deck);
    	game.setPlayer(player);
		
    	if(player.getPoint().burstCheck() || player.getPoint().bjCheck()) {
    		new Stand(game);
			url = Stand.getUrl();
			return;
		}
    	
		url = "PlayerTurn";
	}
	
	public static String getUrl() {
		return url;
	}
}
