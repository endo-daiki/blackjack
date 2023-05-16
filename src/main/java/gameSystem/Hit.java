package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Game;

public class Hit {
	private static String url;
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
		hand.add(deck.Draw());
		game.setPlayerHand(hand);
		game.setPlayerPoint(PointCalc.Calc(hand));
		
		pointCheck(PointCalc.Calc(hand));
		
		return url;
	}
	
	private static void pointCheck(int point) {
		if(point > 21) {
			game.setPlayerBurst(true);
			
			new Stand(request);
			url = Stand.getUrl();	
		} 
		if(point == 21) {			
			new Stand(request);
			url = Stand.getUrl();	
		}
	}
}