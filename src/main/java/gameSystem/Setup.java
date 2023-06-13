package gameSystem;

import model.Game;

public class Setup {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		player.draw(deck, "normal");
		player.draw(deck, "normal");  	

		dealer.draw(deck, "normal");
		dealer.draw(deck, "normal");

		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		if(player.getPoint("normal").bjCheck()) {
			return Stand.excute(game, "normal");
		}

		return "PlayerTurn";
	}
}