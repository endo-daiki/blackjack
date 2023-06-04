package gameSystem;

import model.Game;

public class Split {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player split = game.getSplit();
		
		Hand playerHand = player.getHand();
		Card card1 = playerHand.getList().get(0);
		Card card2 = playerHand.getList().get(1);
		
		deck.add(card1);
		deck.add(card2);
		
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
    		return Stand.excute(game);
		}
			
		return "PlayerTurn";
	}
}
