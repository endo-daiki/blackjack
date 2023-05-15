package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.UserEdit;
import model.User;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEdit")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, 
//			HttpServletResponse response) 
//					throws ServletException, IOException {
//		
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("userEdit.jsp");
//		dispatcher.forward(request, response);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
			request.setCharacterEncoding("UTF-8");
	
			String oldId = request.getParameter("oldId");
			String newId = request.getParameter("newId");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String checkPassword = request.getParameter("checkPassword");
			
			User user = new User(oldId, newId, name, password, checkPassword);
			RequestDispatcher dispatcher = UserEdit.edit(user, request);
			
			dispatcher.forward(request, response);
	}

}

