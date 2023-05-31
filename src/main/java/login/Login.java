package login;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Select;
import model.User;

public class Login {
	public static String userLogin(User user, HttpServletRequest request) {
		String url = "/login.jsp";	
		Select select = new Select();
		
		if(!Validation.validationId(user.getId())) {
			request.setAttribute("error_msg", "IDを入力してください");
			return url;
		}
		if(!Validation.validationPassword(user.getPassword())) {
			request.setAttribute("error_msg", "パスワードを入力してください");
			return url;
		}
		User loginUser = select.selectUser(user.getId(), user.getPassword());
		if(loginUser == null) {
			request.setAttribute("error_msg", "アカウントが存在しないか、IDまたはパスワードが間違っています");
			return url;
		} 
		
		url = "/Main";	
		
		HttpSession session = request.getSession(true);
	    session.setAttribute("user", loginUser);
	    
		return url;
	}
}
