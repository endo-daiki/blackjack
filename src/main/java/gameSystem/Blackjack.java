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
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		Player player = game.getPlayer();
		Player dealer = game.getDealer();
		Bet bet = game.getBet();
		
		bet.judge(player, dealer);  

		Update.updateResult(id, bet.refund());
		Insert.insertLog(id, bet.refund());
		
		session.setAttribute("user", Select.selectUser(id, user.getPassword()));
		
		game.setPlayer(player);
		game.setDealer(dealer);
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

