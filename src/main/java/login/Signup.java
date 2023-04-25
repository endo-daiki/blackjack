package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Database;
import model.User;

public class Signup {
	public static RequestDispatcher SignupCheck(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/jsp/signupDone.jsp");;
		boolean check = true;
		
		if(!Validation.validationId(user.getId())) {
			check = false;
			request.setAttribute("error_id", "IDを入力してください");
		}
		if(!Validation.validationName(user.getName())) {
			check = false;
			request.setAttribute("error_name", "名前を入力してください");
		}
		if(!Validation.validationPassword(user.getPassword())) {
			check = false;
			request.setAttribute("error_password", "パスワードを入力してください");
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			check = false;
			request.setAttribute("error_check", "パスワードが正しくありません");
		}
		
		
		if(check == true) {
			if(!Database.insertUser(user.getId(), user.getName(), user.getPassword())) {
				request.setAttribute("error_id", "このIDはすでに使われています");
				dispatcher = 
						request.getRequestDispatcher("/jsp/signup.jsp");	
			}
		} else {
			request.setAttribute("error_id", "このIDはすでに使われています");
			dispatcher = 
					request.getRequestDispatcher("/jsp/signup.jsp");
		}
		
		return dispatcher;
	}
}
