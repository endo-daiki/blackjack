package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Delete;
import model.User;

public class UserDelete {
	public static String excute(String id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		
		if(!id.equals(user.getId())) {
			throw new IllegalArgumentException("Idが正しくありません。不正があります。");
		}
		session.invalidate();

		Delete.deleteUser(id);
		Delete.deleteLog(id);

		return "userDeleteDone.jsp";
	}
}
