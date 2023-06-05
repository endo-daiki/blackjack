package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
			
		if(player.getResult().equals("split")) {
			Player split = game.getSplit();
			split.draw(deck);
			
			game.setDeck(deck);
	    	game.setSplit(split);
	    	
			if(split.getPoint().burstCheck() || split.getPoint().bjCheck()) {
				split.setResult("stand");
				return Stand.excute(game);
			}
			
			return "PlayerTurn";
		} else {
			player.draw(deck);
			
			game.setDeck(deck);
	    	game.setPlayer(player);
	    	
			if(player.getPoint().burstCheck() || player.getPoint().bjCheck()) {
	    		player.setResult("stand");
				return Stand.excute(game);
			}
			
			return "PlayerTurn";
		}
	}
}
