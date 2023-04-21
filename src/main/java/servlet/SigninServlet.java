package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class SigninServlet
 */
@WebServlet("/Signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SigninServlet() {
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
				request.getRequestDispatcher("jsp/signin.jsp");
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
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		boolean insertCheck = true;
		
		if(name.length() == 0) {
			request.setAttribute("error_name", "名前を入力してください");
			insertCheck = false;
		}
		if(nickname.length() == 0) {
			request.setAttribute("error_nickname", "ニックネームを入力してください");
			insertCheck = false;
		}
		if(password.length() == 0) {
			request.setAttribute("error_password", "パスワードを入力してください");
			insertCheck = false;
		}
		if(checkPassword.length() == 0 && !checkPassword.equals(password)) {
			request.setAttribute("error_check", "パスワードが正しくありません");
			insertCheck = false;
		}
		
		if(insertCheck == false) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/jsp/signin.jsp");
			dispatcher.forward(request, response);
		} 
		
//		User user = new User();
//		user.insertUser(name, nickname, password, checkPassword);
		
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("/jsp/signinDone.jsp");
//		dispatcher.forward(request, response);
		
	}

}
