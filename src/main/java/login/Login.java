package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;

public class Login {
	public static RequestDispatcher userLoginCheck(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");	
		
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return dispatcher;
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return dispatcher;
		}
		User loginUser = Select.selectUser(user.getId(), user.getPassword());
		if(loginUser == null) {
			request.setAttribute("error_msg", "アカウントが存在しないか、IDまたはパスワードが間違っています");
			return dispatcher;
		} 
		
		dispatcher = request.getRequestDispatcher("/Main");	
		HttpSession session = request.getSession(true);
	    session.setAttribute("user", loginUser);
		return dispatcher;
	}
}
