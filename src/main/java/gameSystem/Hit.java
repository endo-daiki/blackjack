package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game, String select) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		if(select.equals("split")) {
			player.setStatus(Status.SPLIT);
		} else {
			player.setStatus(Status.PLAYING);
		}
		
		player.draw(deck);
		if(player.moveCheck()) {
			return Stand.excute(game, select);
		}
		
		game.setDeck(deck);
		game.setPlayer(player);

		return "PlayerTurn";
	}
}
