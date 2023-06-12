package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		if(player.getStatus() == Status.SPLIT) {
			player.splitDraw(deck);
			
			if(player.getSplitPoint().bjCheck() || player.getSplitPoint().burstCheck()) {
				return Stand.excute(game);
			}
		} else {
			player.draw(deck);
			
			if(player.getPoint().bjCheck() || player.getPoint().burstCheck()) {
				return Stand.excute(game);
			}
		}	
		game.setDeck(deck);
		game.setPlayer(player);
		
		return "PlayerTurn";
	}
}
