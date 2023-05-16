package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Game;

public class Hit {
	public static String url;
	private static HttpSession session;
	private static Game game;
	private static Deck deck;
	private static HttpServletRequest request;
	
	public Hit(HttpServletRequest request) {
		this.request = request;
		session = request.getSession(true);
		game = Blackjack.game;
		deck = Blackjack.deck;
		url = "PlayerTurn";
	}
	
	public static String getUrl() {
		if(session.getAttribute("user") == null || game == null) {
			url = "/blackjack";
			return url;
		}
		if(game.getFinished() == true) {
			url = "Result";
			return url;
		}
		
		List<Card> hand = game.getPlayerHand();
		Deck deck = Blackjack.deck;
		url = "PlayerTurn";
		
		hand.add(deck.Draw());
		
		game.setPlayerHand(hand);
		int point = PointCalc.Calc(hand);
		game.setPlayerPoint(point);
		
		if(point > 21) {
			game.setPlayerBurst(true);
			
			new Stand(request);
			url = Stand.getUrl();
			
			return url;
		} else if(PointCalc.Calc(hand) == 21) {			
			new Stand(request);
			url = Stand.getUrl();
			
			return url;
		}
		
		return url;
	}
}
