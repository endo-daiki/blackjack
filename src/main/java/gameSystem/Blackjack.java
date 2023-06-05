package gameSystem;

import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Update;
import model.Game;

public class Blackjack {
	private static Game game;
	private static String id;

	public Blackjack(int bet, String user_id) {
		game = new Game(bet);
		id = user_id;
	}

	public static String getGame(HttpServletRequest request) {
		request.setAttribute("game", game);

		if(!game.getGameResult().equals("playing")) {
			Bet bet = game.getBet();
			bet.calc(game.getPlayer().getResult());
			bet.calc(game.getSplit().getResult());    

			Update.updateResult(id, bet.refund());
			Insert.insertLog(id, bet.refund());

			game.setBet(bet);

			return "result.jsp";
		}

		return "playerTurn.jsp";
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

