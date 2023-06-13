package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game, String key) {
		if(key == null) {
			return "PlayerTurn";
		}
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
//		player.draw(deck, key);
//		if(player.moveCheck(key)) {
//			return Stand.excute(game, key);
//		}
		
		if(key.equals("split")) {
			player.setStatus(Status.SPLIT);
		} else {
			player.setStatus(Status.PLAYING);
		}
		
		player.draw(deck);
		if(player.moveCheck()) {
			return Stand.excute(game, key);
		}
		
		game.setDeck(deck);
		game.setPlayer(player);

		return "PlayerTurn";
	}
}
