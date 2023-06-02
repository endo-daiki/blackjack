package gameSystem;

import model.Game;

public class Setup {
	private static String url;
	
	Setup(Game game) {
		Deck deck = game.getDeck();
		
    	Player player = game.getPlayer();
    	player.setResult("playing");
    	
    	player.draw(deck);
    	player.draw(deck);  	
    	game.setPlayer(player);
    	
    	Player dealer = game.getDealer();
    	dealer.draw(deck);
    	dealer.draw(deck);
    	game.setDealer(dealer);
    	
    	game.setDeck(deck);
    	
    	game.setGameResult("playing");
    	
    	if(player.getPoint().bjCheck()) {
    		player.setResult("stand");
			new Stand(game);
			url = Stand.getUrl();
			return;
		}
		
		url = "PlayerTurn";
	}
	
	static String getUrl() {
		return url;
	}
}
