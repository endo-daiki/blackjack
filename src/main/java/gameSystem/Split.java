package gameSystem;

import model.Game;

public class Split {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		
		Hand playerHand = player.getHand();
		Card card1 = playerHand.getList().get(0);
		Card card2 = playerHand.getList().get(1);

		deck.add(0, card1);
		deck.add(2, card2);

		Player newPlayer = new Player();
		newPlayer.draw(deck);
		newPlayer.draw(deck);
		
		newPlayer.splitDraw(deck);
		newPlayer.splitDraw(deck);
		
		newPlayer.setStatus(Status.SPLIT);

		game.setDeck(deck);
		game.setPlayer(newPlayer);

		if(newPlayer.getSplitPoint().bjCheck()) {
			return Stand.excute(game);
		}

		return "PlayerTurn";
	}
}
