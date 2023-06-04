package gameSystem;

import model.Game;

public class Setup {
protected static String excute(Game game) {
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
    		return Stand.excute(game);
		}
		
		return "PlayerTurn";
	}
}
