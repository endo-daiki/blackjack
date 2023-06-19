package gameSystem;

import model.Game;

public class Setup {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		player.draw(deck.pull(), Status.PLAYING);
		player.draw(deck.pull(), Status.PLAYING);  	

		dealer.draw(deck.pull(), Status.PLAYING);
		dealer.draw(deck.pull(), Status.PLAYING);
		
		game.setPlayer(player);
		game.setDealer(dealer);
		game.setDeck(deck);

		if(player.getPoint(Status.PLAYING).bjCheck()) {
			return Stand.excute(game, "PLAYING");
		}

		return "PlayerTurn";
	}
}