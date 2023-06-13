package gameSystem;

import model.Game;

public class Setup {
	protected static String excute(Game game) {
		Deck deck = game.getDeck();
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		
		Card card = new Card("heart", CardNumber.one);
		deck.add(0, card);
		deck.add(0, card);

		player.draw(deck, "normal");
		player.draw(deck, "normal");  	

		dealer.draw(deck, "normal");
		dealer.draw(deck, "normal");
		
		game.setPlayer(player);
		game.setDealer(dealer);
		game.setDeck(deck);

		if(player.getPoint("normal").bjCheck()) {
			return Stand.excute(game, "normal");
		}

		return "PlayerTurn";
	}
}