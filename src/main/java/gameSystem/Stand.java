package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game, String select) {
		if(select == null) {
			return "PlayerTurn";
		}
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		if(select.equals("split")) {
			player.setStatus(Status.SPLIT);
		} else {
			player.setStatus(Status.PLAYING);
		}

		player.isStand();
		
		if(!player.moveCheck()) {
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
