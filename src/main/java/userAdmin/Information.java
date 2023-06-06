package userAdmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;
import model.playLog;

public class Information {
	public static String getPlayInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		User userInfo = Select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);

		List<User> ranker = Select.selectRanker();
		request.setAttribute("ranker", ranker);

		List<playLog> playLog = Select.selectPlayLog(user.getId());
		request.setAttribute("playLog", playLog);

		return "playInfo.jsp";
	}

	public static String getUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");

		User userInfo = Select.selectUser(user.getId(), user.getPassword());
		request.setAttribute("user", userInfo);

		return "userInfo.jsp";
	}
}
