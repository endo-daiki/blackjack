package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Game;
import model.User;

public class Setup {
	public static String url;
	
	public Setup(HttpServletRequest request) {      
		url = "PlayerTurn";
		
		HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user"); 
        if(user == null) {
        	url = "/blackjack";
        }
		
        new Blackjack(user.getId());
        Game game = Blackjack.game;
        Deck deck = Blackjack.deck;
		
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
		}
		
	}
	
	public static String getUrl() {
		return url;
	}
}
