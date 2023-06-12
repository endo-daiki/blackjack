package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		if(player.getStatus() == Status.SPLIT) {
			player.setStatus(Status.PLAYING);
			if(player.getPoint().bjCheck()) {
				return excute(game);
			}
			return "PlayerTurn";
		}

		if(!player.getPoint().burstCheck() || !player.getSplitPoint().burstCheck() && player.getSplitHand().sizeCheck() > 0) {
			while(dealer.getPoint().getScore() < 17) {
				dealer.draw(deck);
			}
		}

		player.judge(dealer);
		
		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		return "Result";
	}
}
