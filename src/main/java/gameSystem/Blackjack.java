package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import model.Game;

public class Blackjack {
	public static Game game;
	public static Deck deck;
    
    public Blackjack(String id) {
    	game = new Game(id);
    	deck = new Deck();
    }
    
    public static Game getGame() {
    	return game;
    }
    public static Deck getDeck() {
    	return deck;
    }

    public static RequestDispatcher getPlayerTurn(HttpServletRequest request) {
    	request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("playerTurn.jsp");
		
		return dispatcher;
    }
    
    public static RequestDispatcher getResult(HttpServletRequest request) {
    	if(game == null) {
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("main.jsp");
    		
    		return dispatcher;
    	}
    	
    	request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("result.jsp");
		
		return dispatcher;
    }
	
	public static void resetGame() {
		game = null;
	}
}
