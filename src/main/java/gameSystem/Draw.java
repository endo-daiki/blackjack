package gameSystem;

import java.util.List;

import model.Card;
import model.Game;

public class Draw {
	public void playerDraw(Game game) {
		Deck deck = game.getDeck();
		List<Card> playerHand = game.getPlayerHand();		
		
		playerHand.add(deck.Draw());
		
		game.setDeck(deck);
		game.setPlayerHand(playerHand);
		game.setPlayerPoint(PointCalc.Calc(playerHand));
		
		if(game.getPlayerPoint() > 21) {
			game.setPlayerBurst(true);
		}
	}
	
	public void declerDraw(Game game) {
		Deck deck = game.getDeck();
		List<Card> dealerHand = game.getDealerHand();
		
		dealerHand.add(deck.Draw());
		
		game.setDeck(deck);
		game.setDealerHand(dealerHand);
		game.setDealerPoint(PointCalc.Calc(dealerHand));
		
		if(game.getDealerPoint() > 21) {
			game.setDealerBurst(true);
		}
	}
}
