package gameSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import model.Game;
import model.User;

public class Blackjack {
	private static Game game;
	private static String id;

	public Blackjack(int bet, String user_id) {
		game = new Game(bet);
		id = user_id;
	}
	public static String getPlayerTurn(HttpServletRequest request) {
		request.setAttribute("game", game);
		return "playerTurn.jsp";
	}
	public static String getResult(HttpServletRequest request) {		
		Bet bet = game.getBet();
		Update.updateResult(id, bet.refund());
		Insert.insertLog(id, bet.refund());

		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		session.setAttribute("user", Select.selectUser(id, user.getPassword()));

		game.setBet(bet);
		request.setAttribute("game", game);
		return "result.jsp";
	}
	public static String Setup() {    	
		return Setup.excute(game);
	}
	public static String Hit(String select) {
		return Hit.excute(game, select);
	}
	public static String Stand(String select) {
		return Stand.excute(game, select);
	}
	public static String Split() {
		return Split.excute(game);
	}
}

