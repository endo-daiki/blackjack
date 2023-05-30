package gameSystem;

import model.Game;

public class Setup {
	private static String url;
	Setup() {
		url = "playerTurn.jsp";
		//ここで、gameからの情報を変数にしたほうがわかりやすい?
	}
	
	Setup(Game game) {
		Deck deck = game.getDeck();
    	Player player = game.getPlayer();
    	player.Draw(deck);
    	player.Draw(deck); 
    	
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
