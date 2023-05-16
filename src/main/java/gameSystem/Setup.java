package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Game;
import model.User;

public class Setup {
	public static String url;
	private static HttpSession session;
	private static Game game;
	private static Deck deck;
	private static HttpServletRequest request;
	
	public Setup(HttpServletRequest request) { 
		this.request = request;
		session = request.getSession(true);
		User user = (User)session.getAttribute("user"); 
		new Blackjack(user.getId());
		
		game = Blackjack.game;
		deck = Blackjack.deck;
		url = "PlayerTurn";
	}
	
	public static String getUrl() {		
        User user = (User)session.getAttribute("user"); 
        if(user == null) {
        	url = "/blackjack";
        	return url;
        }
		
		List<Card> hand = game.getPlayerHand();
		hand.add(deck.Draw());
		hand.add(deck.Draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(PointCalc.Calc(hand));
		
		List<Card> dealerHand = game.getDealerHand();
		dealerHand.add(deck.Draw());
		dealerHand.add(deck.Draw());
		
		game.setDealerHand(dealerHand);
		
		if(game.getPlayerPoint() == 21) {
			new Stand(request);
			url = Stand.getUrl();
			
			return url;
		}
		
		return url;
	}
}
