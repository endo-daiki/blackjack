package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Update;
import model.Game;

public class Blackjack {
	private static Game game;
	private static String url;
    
    public Blackjack(String id) {
    	game = new Game(id);
    	url = "playerTurn.jsp";
    }

    public static String getGame(HttpServletRequest request) {
    	Update update = new Update();
    	Insert insert = new Insert();
    	Player player = game.getPlayer();
    	
    	request.setAttribute("game", game);
    	if(!(player.getResult().equals("playing"))) {
    		update.updateResult(game.getUserId(), game.getResult());
    		insert.insertLog(game.getUserId(), game.getResult());
    		
    		url = "result.jsp";
    		return url;
    	}
		
    	url = "playerTurn.jsp";
		return url;
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

