package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameSystem.Blackjack;

public class Logout {
	public static RequestDispatcher logout(HttpServletRequest request) {
		RequestDispatcher dispatcher;
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		Blackjack.resetGame();
		
		dispatcher = request.getRequestDispatcher("logout.jsp");
		
		return dispatcher;
	}
}
