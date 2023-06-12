package gameSystem;

import model.Game;

public class Setup {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();

		Player player = game.getPlayer();
		
		Card card = new Card("heart", CardNumber.one);
		deck.add(0, card);
		deck.add(0, card);

		player.draw(deck);
		player.draw(deck);  	
		game.setPlayer(player);

		Player dealer = game.getDealer();
		dealer.draw(deck);
		dealer.draw(deck);
		game.setDealer(dealer);

		game.setDeck(deck);

		if(player.getPoint().bjCheck()) {
			return Stand.excute(game);
		}

		return "PlayerTurn";
	}
}