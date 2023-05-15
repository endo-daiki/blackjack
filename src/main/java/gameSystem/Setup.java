package gameSystem;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Card;
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
        Game game = Blackjack.getGame();
        Deck deck = new Deck();
		
		List<Card> hand = new ArrayList<Card>();
		hand.add(deck.Draw());
		hand.add(deck.Draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(PointCalc.pointCalc(hand));
		
		List<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(deck.Draw());
		dealerHand.add(deck.Draw());
		
		game.setDealerHand(dealerHand);
		
		game.setDeck(deck.getDeck());
		
		if(game.getPlayerPoint() == 21) {
			new Stand(request);
			url = Stand.getUrl();
		}
		
	}
	
	public static String getUrl() {
		return url;
	}
}
