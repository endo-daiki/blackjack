package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Select;
import model.User;

public class Signup {
	public static RequestDispatcher userSignup(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
		
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return dispatcher;
		}
		if(!Validation.validationName(user.getName())) {
			request.setAttribute("error_msg", "名前を入力してください");
			return dispatcher;
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return dispatcher;
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			request.setAttribute("error_msg", "パスワードが正しくありません");
			return dispatcher;
		}
		if(Select.selectId(user.getId())) {
			request.setAttribute("error_msg", "このIDは既に使われています。");
			return dispatcher;
		}
			
		dispatcher = request.getRequestDispatcher("signupDone.jsp");

		Insert.insertUser(user.getId(), user.getName(), user.getPassword());
		
		return dispatcher;
	}
}
