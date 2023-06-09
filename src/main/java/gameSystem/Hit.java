package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		player.draw(deck);
		game.setDeck(deck);
		game.setPlayer(player);
		
		if(player.getPoint().bjCheck() || player.getPoint().burstCheck()) {
			return Stand.excute(game);
		}
		
		return "PlayerTurn";
	}
}
