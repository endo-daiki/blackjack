package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Delete;

public class UserDelete {
	public static RequestDispatcher excute(String id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		
		Delete.deleteUser(id);
		Delete.deleteLog(id);
			        
	    RequestDispatcher dispatcher = 
			request.getRequestDispatcher("userDeleteDone.jsp");
		
		return dispatcher;
	}
}
