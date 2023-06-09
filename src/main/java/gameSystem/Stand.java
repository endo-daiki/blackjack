package gameSystem;

import model.Game;

public class Stand {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		if(player.getSplitHand().getResult() == Result.STAND) {
			player.setResult(Result.PLAYING);
			return "PlayerTurn";
		}

		if(!player.getPoint().burstCheck() || !player.getSplitPoint().burstCheck() && !player.getSplitHand().getResult().equals(Result.READY)) {
			while(dealer.getPoint().getScore() < 17) {
				dealer.draw(deck);
			}
		}

		player.judge(dealer);
		game.setGameResult("test");

		game.setDeck(deck);
		game.setPlayer(player);
		game.setDealer(dealer);

		return "Result";
	}
}
