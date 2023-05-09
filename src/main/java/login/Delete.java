package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;
import gameSystem.Blackjack;

public class Delete {
	public static RequestDispatcher UserDelete(String id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		
		Database.deleteUser(id);
		
		Blackjack.resetGame();
	        
	    RequestDispatcher dispatcher = 
			request.getRequestDispatcher("login.jsp");
		
		return dispatcher;
	}
}
