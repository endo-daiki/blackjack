package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game, String key) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		player.draw(deck, key);
		
		if(player.movedCheck(key)) {
			return Stand.excute(game, key);
		}

		game.setDeck(deck);
		game.setPlayer(player);

		return "PlayerTurn";
	}
}
