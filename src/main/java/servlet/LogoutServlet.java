package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userAdmin.Logout;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {

		String url = Logout.userLogout(request);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, 
//			HttpServletResponse response) 
//					throws ServletException, IOException {
//
//
//	}

}
