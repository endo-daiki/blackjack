package gameSystem;

import model.Game;

public class Split {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		player.split();
		player.draw(deck, "normal");
		player.draw(deck, "split");

		if(player.getPoint("split").bjCheck()) {
			return Stand.excute(game, "split");
		}
		if(player.getPoint("normal").bjCheck()) {
			return Stand.excute(game, "normal");
		}

		return "PlayerTurn";
	}
}
