package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Select;
import model.User;

public class Signup {
	public static String userSignup(User user, HttpServletRequest request) {
		String url = "signup.jsp";
		
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return url;
		}
		if(!Validation.validationName(user.getName())) {
			request.setAttribute("error_msg", "名前を入力してください");
			return url;
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return url;
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			request.setAttribute("error_msg", "パスワードが正しくありません");
			return url;
		}
		if(Select.selectId(user.getId())) {
			request.setAttribute("error_msg", "このIDは既に使われています。");
			return url;
		}
			
		url = "signupDone.jsp";

		Insert.insertUser(user.getId(), user.getName(), user.getPassword());
		
		return url;
	}
}
