package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import database.Update;
import model.User;

public class UserEdit {
	public static RequestDispatcher edit(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("userEdit.jsp");
		
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_id", "IDを入力してください");
			return dispatcher;
		}
		new Select();
		if(Select.selectId(user.getNewId()) && !(user.getNewId().equals(user.getId()))) {
			request.setAttribute("error_id", "このIDは既に使われています。");
			return dispatcher;
		}
		if(!Validation.validationName(user.getName())) {
			request.setAttribute("error_name", "名前を入力してください");
			return dispatcher;
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_password", "パスワードを入力してください");
			return dispatcher;
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			request.setAttribute("error_check", "パスワードが正しくありません");
			return dispatcher;
		}
		
		
		dispatcher = 
				request.getRequestDispatcher("userEditDone.jsp");

		new Update();
		Update.updateUser(user.getId(), user.getNewId(), user.getName(), user.getPassword());
		
		User updateUser = Select.selectUser(user.getNewId(), user.getPassword());
		
		HttpSession session = request.getSession(true);
	    session.setAttribute("user", updateUser);

		return dispatcher;
	}
}
