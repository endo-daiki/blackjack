package gameSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

public class Player {
	private static HttpSession session;
	private static User user;
	
	public Player(HttpServletRequest request) {
		session = request.getSession(true);
		user = (User)session.getAttribute("user");
	}
	
	public static boolean loginChecker() {
		if(user == null) {
			return false;
		}
		return true;
	}
}
