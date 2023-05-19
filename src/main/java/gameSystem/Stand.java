package gameSystem;

import model.Game;

public class Stand {
	private static String url;
	
	Stand(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		if(player.getPoint().burstCheck()) {
			game.setResult("lose");
			return;
		} 
		
		while(dealer.getPoint().point< 17) {
			dealer.Draw(deck);
		}
		
		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);
				
		if(dealer.getPoint().burstCheck() || player.getPoint().point > dealer.getPoint().point) {
			game.setResult("win");
		} else if (player.getPoint().burstCheck() || player.getPoint().point < dealer.getPoint().point) {
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
