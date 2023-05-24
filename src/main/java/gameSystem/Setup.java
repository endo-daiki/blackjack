package gameSystem;

import model.Game;

public class Setup {
	private static String url;
	
	Setup(Game game) {
		Deck deck = game.getDeck();
    	Player player = game.getPlayer();   	
    	player.Draw(deck);
    	player.Draw(deck); 
 	
//    	if(player.getHand().splitCheck()) {
//    		Player spliter = new Player();
//    		player.split(spliter);
//    		player.Draw(deck);
//    		spliter.Draw(deck);
//    		game.setSpliter(spliter);
//    	}
    	
    	game.setPlayer(player);
    	
    	Player dealer = game.getDealer();
    	dealer.Draw(deck);
    	dealer.Draw(deck);
    	game.setDealer(dealer);
    	
    	game.setDeck(deck);
    	game.setResult("playing");
    	
    	if(player.getPoint().bjCheck()) {
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
