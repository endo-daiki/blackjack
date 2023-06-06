package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player split = game.getSplit();
		Player dealer = game.getDealer();

		if(player.getResult().equals("split")) {
			player.setResult("playing");
			split.setResult("stand");

			if(player.getPoint().bjCheck()) {
				return excute(game);
			}
			return "PlayerTurn";
		}

		if(!player.getPoint().burstCheck() || !split.getPoint().burstCheck()) {
			while(dealer.getPoint().getScore() < 17) {
				dealer.draw(deck);
			}
		}

		player.judge(dealer);
		split.judge(dealer);
		game.setGameResult(player.getResult());

		game.setDeck(deck);
		game.setPlayer(player);
		game.setSplit(split);
		game.setDealer(dealer);

		return "Result";
	}
}
