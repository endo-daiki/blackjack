package gameSystem;

import model.Game;

public class Split {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();

		player.split();
		player.draw(deck.pull(), Status.PLAYING);
		player.draw(deck.pull(), Status.SPLIT);

		if(player.getPoint(Status.SPLIT).bjCheck()) {
			return Stand.excute(game, "SPLIT");
		}
		if(player.getPoint(Status.PLAYING).bjCheck()) {
			return Stand.excute(game, "PLAYING");
		}

		game.setDeck(deck);
		game.setPlayer(player);

		return "PlayerTurn";
	}
}
