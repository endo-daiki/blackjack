package login;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;
import model.playLog;

public class Information {
	public static String getPlayInfo(HttpServletRequest request) {
		String url;
		Select select = new Select();
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		User userInfo = select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);

		List<User> ranker = select.selectRanker();
		request.setAttribute("ranker", ranker);
		
		List<playLog> playLog = select.selectPlayLog(user.getId());
		request.setAttribute("playLog", playLog);
		
		url = "playInfo.jsp";
		
		return url;
	}
	
	public static String getUserInfo(HttpServletRequest request) {
		String url;
		Select select = new Select();
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		User userInfo = select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);
		
		url = "userInfo.jsp";
		
		return url;
	}
}
