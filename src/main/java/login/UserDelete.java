package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameSystem.Blackjack;
import database.Delete;

public class UserDelete {
	public static RequestDispatcher delete(String id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		
		new Delete();
		Delete.deleteUser(id);
		Delete.deleteLog(id);
		
		Blackjack.resetGame();
	        
	    RequestDispatcher dispatcher = 
			request.getRequestDispatcher("userDeleteDone.jsp");
		
		return dispatcher;
	}
}
