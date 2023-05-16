package gameSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import login.Login;
import model.Game;
import model.User;

public class Stand {
	private static String url;
	private static HttpSession session;
	private static Game game;
	private static HttpServletRequest request;
	
	public Stand(HttpServletRequest request) {
		this.request = request;
		session = request.getSession(true);
		game = Blackjack.game;
		url = "Result";
	}
	
	public static String getUrl() {
		if(game == null || Login.loginCheck(request) == false) {
			url = "/blackjack";
			return url;
		}
		if(game.getFinished() == true) {
			return url;
		}
	
		dealerTurn();
		playLog();
	
		return url;
	}
	
	private static void playLog() {
		Update.updateResult(game.getUserId(), game.getResult());
		
		Insert.insertLog(game.getUserId(), game.getResult());

		User user = (User) session.getAttribute("user");
		user = Select.selectUser(user.getId(), user.getPassword());
		
		session.setAttribute("user", user);
		request.setAttribute("game", game);		
	}
	
	private static void dealerTurn() {
		game.setFinished(true);
		game.setDealerPoint(PointCalc.Calc(game.getDealerHand()));
		
		if(game.getPlayerBurst()) {
			game.setResult("lose");
			return;
		} 

		while(game.getDealerPoint() < 17) {
			Blackjack.dealerDraw();
		}
		
		gameResult();
	}
	
	private static void gameResult() {
		if(game.getDealerBurst() || game.getPlayerPoint() > game.getDealerPoint()) {
			game.setResult("win");
		} else if (game.getPlayerPoint() < game.getDealerPoint() || game.getPlayerBurst()) {
			game.setResult("lose");
		} else {
			game.setResult("draw");
		}
	}
}
