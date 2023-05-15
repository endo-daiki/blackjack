package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;

public class Login {
	public static RequestDispatcher login(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("Main");
		boolean check = true;
		
		if(!Validation.validationId(user.getId())) {
			check = false;
			request.setAttribute("error_id", "IDを入力してください");
		}
		if(!Validation.validationPassword(user.getPassword())) {
			check = false;
			request.setAttribute("error_password", "パスワードを入力してください");
		}
		new Select();
		User loginUser = Select.selectUser(user.getId(), user.getPassword());
		if(loginUser == null) {
			check = false;
			request.setAttribute("error_login", "ログインに失敗しました。アカウントが存在しないか、IDまたはパスワードが間違っています");
		} 
		
		if(check == false) {
			dispatcher = 
					request.getRequestDispatcher("login.jsp");	
		} else {
			HttpSession session = request.getSession(true);
		    session.setAttribute("user", loginUser);
		}
		return dispatcher;
	}
}
