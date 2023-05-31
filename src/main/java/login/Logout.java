package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout {
	public static String userLogout(HttpServletRequest request) {		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String url = "logout.jsp";
		
		return url;
	}
}
