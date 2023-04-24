package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Login;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User user = new User();
		boolean check = user.userLogin(name, password);
		
		
		if(check == false) {
			request.setAttribute("error_check", "ログインに失敗しました。名前またはパスワードが正しくありません");
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("user_login", "ログインしました。");
			
			HttpSession session = request.getSession(true);
	        session.setAttribute("user", user);
		
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
