package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Update;
import model.Game;

public class Blackjack {
	public static Game game;
	private static String url;
    
    public Blackjack(String id) {
    	game = new Game(id);
    	url = "PlayerTurn";
    }
    public static void resetGame() {
    	game = null;
    }

    public static RequestDispatcher getGame(HttpServletRequest request) {
    	RequestDispatcher dispatcher;
    	request.setAttribute("game", game);
    	if(!(game.getResult().equals("playing"))) {
    		Update.updateResult(game.getUserId(), game.getResult());
    		Insert.insertLog(game.getUserId(), game.getResult());
    		
    		dispatcher = request.getRequestDispatcher("result.jsp");
    		return dispatcher;
    	}
		
    	dispatcher = request.getRequestDispatcher("playerTurn.jsp");
		return dispatcher;
    }
    
    public static String Setup() {    	
    	new Setup(game);
    	url = Setup.getUrl();
    	return url;
    }
	
	public static String Hit() {
		new Hit(game);
		url = Hit.getUrl();
		return url;
	}
	
	public static String Stand() {
		new Stand(game);
		url = Stand.getUrl();
		return url;
	}

}

