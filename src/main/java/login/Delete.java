package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gameSystem.Blackjack;

public class Delete {
	public static RequestDispatcher UserDelete(String id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		
		new Delete();
		database.Delete.deleteUser(id);
		
		Blackjack.resetGame();
	        
	    RequestDispatcher dispatcher = 
			request.getRequestDispatcher("userDeleteDone.jsp");
		
		return dispatcher;
	}
}
