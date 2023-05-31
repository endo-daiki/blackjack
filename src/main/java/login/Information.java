package login;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;
import model.playLog;

public class Information {
	public static RequestDispatcher getPlayInfo(HttpServletRequest request) {
		RequestDispatcher dispatcher;
		Select select = new Select();
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		User userInfo = select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);

		List<User> ranker = Select.selectRanker();
		request.setAttribute("ranker", ranker);
		
		List<playLog> playLog = Select.selectPlayLog(user.getId());
		request.setAttribute("playLog", playLog);
		
		dispatcher = request.getRequestDispatcher("playInfo.jsp");
		
		return dispatcher;
	}
	
	public static RequestDispatcher getUserInfo(HttpServletRequest request) {
		RequestDispatcher dispatcher;
		Select select = new Select();
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		User userInfo = select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);
		
		dispatcher = request.getRequestDispatcher("userInfo.jsp");
		
		return dispatcher;
	}
}
