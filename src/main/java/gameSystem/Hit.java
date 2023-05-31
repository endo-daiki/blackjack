package gameSystem;

import model.Game;

public class Hit {
	private static String url;
	
	Hit(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
			
		if(player.result.equals("split")) {
			Player split = game.getSplit();
			split.draw(deck);
			
			game.setDeck(deck);
	    	game.setSplit(split);
	    	
			if(split.getPoint().burstCheck() || split.getPoint().bjCheck()) {
				split.setResult("stand");
	    		new Stand(game);
				url = Stand.getUrl();
				return;
			}
			
			url = "PlayerTurn";
		} else {
			player.draw(deck);
			
			game.setDeck(deck);
	    	game.setPlayer(player);
	    	
			if(player.getPoint().burstCheck() || player.getPoint().bjCheck()) {
	    		player.setResult("stand");
	    		new Stand(game);
				url = Stand.getUrl();
				return;
			}
			
			url = "PlayerTurn";
		}
	}
	
	public static String getUrl() {
		return url;
	}
}
