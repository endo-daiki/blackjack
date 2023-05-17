package gameSystem;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import model.Card;
import model.Game;

public class Blackjack {
	public static Game game;
	public static Deck deck;
    
    public Blackjack(String id) {
    	game = new Game(id);
    	deck = new Deck();
    }

    public static RequestDispatcher getGame(HttpServletRequest request) {
    	request.setAttribute("game", game);
    	RequestDispatcher dispatcher;
    	if(game.getFinished()) {
    		dispatcher = request.getRequestDispatcher("result.jsp");
    		return dispatcher;
    	}
		
    	dispatcher = request.getRequestDispatcher("playerTurn.jsp");
		return dispatcher;
    }
    
	public static void playerDraw() {
		List<Card> hand = game.getPlayerHand();		
		hand.add(deck.Draw());
		game.setPlayerHand(hand);
		game.setPlayerPoint(PointCalc.Calc(hand));
		
		if(game.getPlayerPoint() > 21) {
			game.setPlayerBurst(true);
		}
	}
	
	public static void dealerDraw() {
		List<Card> hand = game.getDealerHand();		
		hand.add(deck.Draw());
		game.setDealerHand(hand);
		game.setDealerPoint(PointCalc.Calc(hand));
		
		if(game.getDealerPoint() > 21) {
			game.setDealerBurst(true);
		}
	}
	
	public static void resetGame() {
		game = null;
		deck = null;
	}
}

