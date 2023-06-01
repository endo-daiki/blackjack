package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Update;
import model.Game;
import model.User;

public class Blackjack {
	private static Game game;
	private static String url;
    
    public Blackjack(int bet) {
    	game = new Game(bet);
    	url = "playerTurn.jsp";
    }

    public static String getGame(HttpServletRequest request) {
    	Update update = new Update();
    	Insert insert = new Insert();
    	Bet bet = game.getBet();
    	HttpSession session = request.getSession(true);
    	User user = (User)session.getAttribute("user");
    	
    	bet.calc(game.getPlayer().result);
    	bet.calc(game.getSplit().result);    		
    	
    	game.setBet(bet);
    	
    	request.setAttribute("game", game);
    	if(!game.getGameResult().equals("playing")) {
    		update.updateResult(user.getId(), bet.refund());
    		insert.insertLog(user.getId(), bet.refund());
    		
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

	public static String Split() {
		new Split(game);
		url = Stand.getUrl();
		return url;
	}
}

