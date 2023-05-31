package gameSystem;

import model.Game;

public class Split {
private static String url;
	
	Split(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player split = game.getSplit();
		
		Hand playerHand = player.getHand();
		Card card1 = playerHand.getList().get(0);
		Card card2 = playerHand.getList().get(1);
		
		deck.add(0, card1);
		deck.add(0, card2);
		
		Player newPlayer = new Player();
		newPlayer.draw(deck);
		split.draw(deck);
		newPlayer.draw(deck);
		split.draw(deck);
		
		split.setResult("playing");
		newPlayer.setResult("split");
		
		game.setDeck(deck);
		game.setPlayer(newPlayer);
		game.setSplit(split);
		
		if(split.getPoint().bjCheck()) {
    		split.setResult("stand");
			new Stand(game);
			url = Stand.getUrl();
			return;
		}
			
		url = "PlayerTurn";
	}
	
	public static String getUrl() {
		return url;
	}
}
