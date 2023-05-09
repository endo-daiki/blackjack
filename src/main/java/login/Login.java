package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;
import model.User;

public class Login {
	public static RequestDispatcher login(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Main");
		boolean check = true;
		
		if(!Validation.validationId(user.getId())) {
			check = false;
			request.setAttribute("error_id", "IDを入力してください");
		}
		if(!Validation.validationPassword(user.getPassword())) {
			check = false;
			request.setAttribute("error_password", "パスワードを入力してください");
		}
		
		User loginUser = Database.loginUser(user.getId(), user.getPassword());
		
		if(check == true) {
			if(loginUser == null) {
				request.setAttribute("error_login", "ログインに失敗しました。アカウントが存在しないか、IDまたはパスワードが間違っています");
				dispatcher = 
						request.getRequestDispatcher("login.jsp");	
			} else {
				HttpSession session = request.getSession(true);
		        session.setAttribute("user", loginUser);
			}
		} else {
			dispatcher = 
					request.getRequestDispatcher("login.jsp");
		}
		
		return dispatcher;
	}
	
//	public static RequestDispatcher login(HttpServletRequest request) {
//		RequestDispatcher dispatcher = null;
//		HttpSession session = request.getSession(true);
//		
//       if(session.getAttribute("user") == null) {
//    	   request.setAttribute("error_login", "ログインされていません。ログインしてください。");
//    	   dispatcher = request.getRequestDispatcher("login.jsp");
//       } else {
//    	   dispatcher = request.getRequestDispatcher("/Main");
//       }
//       
//       return dispatcher;
//	}
}
