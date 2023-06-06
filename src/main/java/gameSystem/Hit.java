package gameSystem;

import model.Game;

public class Hit {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();

		if(player.getResult().equals("split")) {
			Player split = game.getSplit();
			split.draw(deck);

			game.setDeck(deck);
			game.setSplit(split);

			if(split.getPoint().burstCheck() || split.getPoint().bjCheck()) {
				split.setResult("stand");
				return Stand.excute(game);
			}

			return "PlayerTurn";
		} else {
			player.draw(deck);

			game.setDeck(deck);
			game.setPlayer(player);

			if(player.getPoint().burstCheck() || player.getPoint().bjCheck()) {
				player.setResult("stand");
				return Stand.excute(game);
			}

			return "PlayerTurn";
		}	
//		Deck deck = game.getDeck();
//		Player player = game.getPlayer();
//		Player split = game.getSplit();
//		
//		Player hit = player.getResult().equals("split") ? split : player;
//		
//		hit.draw(deck);
//	
//		player = player.getResult().equals("split") ? player : hit;
//		split = player.getResult().equals("split") ? hit : split;
//		
//		game.setDeck(deck);
//		game.setPlayer(player);
//		game.setSplit(split);
//		
//		if(hit.getPoint().burstCheck() || hit.getPoint().bjCheck()) {
//			hit.setResult("stand");
//			return Stand.excute(game);
//		}
//		
//		return "PlayerTurn";
	}
}
