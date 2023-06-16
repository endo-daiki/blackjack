package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game, String key) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		Status status = Status.valueOf(key);
	
		player.isStand(status);
		
		if(!player.movedCheckAll()) {
			return "PlayerTurn";
		}		
		if(!player.burstCheckAll()) {
			while(dealer.getPoint(Status.PLAYING).getScore() < 17) {
				dealer.draw(deck, Status.PLAYING);
			}
		}
		
		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		return "Result";
	}
}
