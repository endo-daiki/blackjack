package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import model.Game;
import model.User;

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
    		
    		HttpSession session = request.getSession(true);
    		
    		User user = (User)session.getAttribute("user");
    		User updateUser = Select.selectUser(user.getId(), user.getPassword());
    		session.setAttribute("user", updateUser);
    		
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

