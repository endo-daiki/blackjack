package gameSystem;

import model.Game;

public class Stand {
	private static String url;
	
	Stand(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player split = game.getSplit();
		Player dealer = game.getDealer();
		
		if(player.getResult() == "split") {
			player.setResult("playing");
			split.setResult("stand");
			
			if(player.getPoint().bjCheck()) {
				new Stand(game);
				url = Stand.getUrl();
				return;
			}
			url = "PlayerTurn";
			return;
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
		
		url = "Result";
	}
	
	public static String getUrl() {
		return url;
	}
}
