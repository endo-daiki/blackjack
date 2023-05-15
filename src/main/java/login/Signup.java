package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import database.Database;
import database.Insert;
import database.Select;
import model.User;

public class Signup {
	public static RequestDispatcher signup(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("signupDone.jsp");
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
		
		new Select();
		if(Select.selectId(user.getId())) {
			check = false;
			request.setAttribute("error_id", "このIDは既に使われています。");
		}
		
		if(check == false) {
			dispatcher =
					request.getRequestDispatcher("signup.jsp");
		} else {
			new Insert();
			Insert.insertUser(user.getId(), user.getName(), user.getPassword());
		}
			
		return dispatcher;
	}
}
