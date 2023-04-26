package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;
import model.User;

public class Edit {
	public static RequestDispatcher editCheck(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = null;
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
		
		
		User updateUser = Database.updateUser(user.getId(), user.getName(), user.getPassword());
		
		if(check == false) {
			dispatcher = 
				request.getRequestDispatcher("userEdit.jsp");
		} else {
			HttpSession session = request.getSession(true);
		    session.setAttribute("user", updateUser);
		        
			dispatcher = 
				request.getRequestDispatcher("userEditDone.jsp");
		} 
		
		return dispatcher;
	}
}
