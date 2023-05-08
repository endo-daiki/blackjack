package login;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Database;
import model.User;

public class Information {
	public static RequestDispatcher PlayInfo(HttpServletRequest request) {
		RequestDispatcher dispatcher = null;
		List<User> ranker = Database.getRanking(); 
		
		request.setAttribute("ranker", ranker);
		dispatcher = request.getRequestDispatcher("playInfo.jsp");
		
		return dispatcher;
	}
}
