package gameSystem;

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
		Bet bet = game.getBet();
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		bet.calc(game.getPlayer().getResult());
		bet.calc(game.getSplit().getResult());    		

		game.setBet(bet);

		request.setAttribute("game", game);
		if(!game.getGameResult().equals("playing")) {
			Update.updateResult(user.getId(), bet.refund());
			Insert.insertLog(user.getId(), bet.refund());

			url = "result.jsp";
			return url;
		}

		url = "playerTurn.jsp";
		return url;
	}

	public static String Setup() {    	
		return Setup.excute(game);
	}

	public static String Hit() {
		return Hit.excute(game);
	}

	public static String Stand() {
		return Stand.excute(game);
	}

	public static String Split() {
		return Split.excute(game);
	}
}

