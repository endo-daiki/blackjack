package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game, String key) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
	
		player.isStand(key);
		
		if(!player.movedCheckAll()) {
			return "PlayerTurn";
		}		
		if(!player.burstCheckAll()) {
			while(dealer.getPoint("normal").getScore() < 17) {
				dealer.draw(deck, "normal");
			}
		}
		
		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		return "Result";
	}
}
