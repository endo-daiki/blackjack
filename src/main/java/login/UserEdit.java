package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import database.Update;
import model.User;

public class UserEdit {
	public static RequestDispatcher excute(User user, HttpServletRequest request) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("userEdit.jsp");
		Select select = new Select();
		
		if(!Validation.validationId(user.getNewId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return dispatcher;
		}
		if(select.selectId(user.getNewId()) && !(user.getNewId().equals(user.getId()))) {
			request.setAttribute("error_msg", "このIDは既に使われています。");
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
		
		dispatcher = request.getRequestDispatcher("userEditDone.jsp");
		
		Update update = new Update();
		
		update.updateUser(user.getId(), user.getNewId(), user.getName(), user.getPassword());	
		User updateUser = select.selectUser(user.getNewId(), user.getPassword());
		
		HttpSession session = request.getSession(true);
	    session.setAttribute("user", updateUser);

		return dispatcher;
	}
}
