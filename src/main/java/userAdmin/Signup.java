package userAdmin;

import javax.servlet.http.HttpServletRequest;

import database.Insert;
import database.Select;
import model.User;

public class Signup {
	public static String userSignup(User user, HttpServletRequest request) {
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return "signup.jsp";
		}
		if(!Validation.validationName(user.getName())) {
			request.setAttribute("error_msg", "名前を入力してください");
			return "signup.jsp";
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return "signup.jsp";
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			request.setAttribute("error_msg", "パスワードが正しくありません");
			return "signup.jsp";
		}
		if(Select.selectId(user.getId())) {
			request.setAttribute("error_msg", "このIDは既に使われています。");
			return "signup.jsp";
		}

		Insert.insertUser(user.getId(), user.getName(), user.getPassword());		
		return "signupDone.jsp";
	}
}
