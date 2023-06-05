package login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import database.Update;
import model.User;

public class UserEdit {
	public static String excute(User user, HttpServletRequest request) {
		if(!Validation.validationId(user.getNewId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return "userEdit.jsp";
		}
		if(Select.selectId(user.getNewId()) && !(user.getNewId().equals(user.getId()))) {
			request.setAttribute("error_msg", "このIDは既に使われています。");
			return "userEdit.jsp";
		}
		if(!Validation.validationName(user.getName())) {
			request.setAttribute("error_msg", "名前を入力してください");
			return "userEdit.jsp";
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return "userEdit.jsp";
		}
		if(!Validation.passwordCheck(user.getPassword(), user.getCheckPassword())) {
			request.setAttribute("error_msg", "パスワードが正しくありません");
			return "userEdit.jsp";
		}

		Update.updateUser(user.getId(), user.getNewId(), user.getName(), user.getPassword());	
		User updateUser = Select.selectUser(user.getNewId(), user.getPassword());

		HttpSession session = request.getSession(true);
		session.setAttribute("user", updateUser);

		return "userEditDone.jsp";
	}
}
