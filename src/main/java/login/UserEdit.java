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
				request.getRequestDispatcher("userEditDone.jsp");
		boolean check = true;
		
		if(!Validation.validationId(user.getId())) {
			check = false;
			request.setAttribute("error_id", "IDを入力してください");
		}
		new Select();
		if(Select.selectId(user.getNewId()) && !(user.getNewId().equals(user.getId()))) {
			check = false;
			request.setAttribute("error_id", "このIDは既に使われています。");
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
		
			
		if(check == false) {
			dispatcher = 
				request.getRequestDispatcher("userEdit.jsp");
		} else {
			new Update();
			Update.updateUser(user.getId(), user.getNewId(), user.getName(), user.getPassword());
			
			User updateUser = Select.selectUser(user.getNewId(), user.getPassword());
			
			HttpSession session = request.getSession(true);
		    session.setAttribute("user", updateUser);
		} 
		
		return dispatcher;
	}
}
