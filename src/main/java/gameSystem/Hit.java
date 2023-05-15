package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Card;
import model.Game;

public class Hit {
	public static String url;
	
	public Hit(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Game game = Blackjack.getGame();
		if(session.getAttribute("user") == null || game == null) {
			url = "/blackjack";
			return;
		}
		if(game.getFinished() == true) {
			url = "Result";
			return;
		}
		
		List<Card> hand = game.getPlayerHand();
		Deck deck = new Deck(game.getDeck());
		url = "PlayerTurn";
		
		hand.add(deck.Draw());
		
		game.setPlayerHand(hand);
		int point = PointCalc.pointCalc(hand);
		game.setPlayerPoint(point);
		
		game.setDeck(deck.getDeck());
		
		if(point > 21) {
			game.setPlayerBurst(true);
			
			new Stand(request);
			url = Stand.getUrl();
		} else if(PointCalc.pointCalc(hand) == 21) {
			new Stand(request);
			url = Stand.getUrl();
		}
	}
	
	public static String getUrl() {
		return url;
	}
}
