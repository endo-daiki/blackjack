package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;

public class Login {
	public static String userLogin(User user, HttpServletRequest request) {
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return "/login.jsp";
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return "/login.jsp";
		}
		User loginUser = Select.selectUser(user.getId(), user.getPassword());
		if(loginUser == null) {
			request.setAttribute("error_msg", "アカウントが存在しないか、IDまたはパスワードが間違っています");
			return "/login.jsp";
		} 

		HttpSession session = request.getSession(true);
		session.setAttribute("user", loginUser);

		return  "/Main";
	}
}
