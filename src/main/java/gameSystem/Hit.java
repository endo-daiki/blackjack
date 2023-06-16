package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game, String key) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Status status = Status.valueOf(key);
		
		player.draw(deck, status);			
		Hand hand = player.getHand().get(status);
		
		if(hand.movedCheck()) {
			return Stand.excute(game, key);
		}

		game.setDeck(deck);
		game.setPlayer(player);

		return "PlayerTurn";
	}
}
