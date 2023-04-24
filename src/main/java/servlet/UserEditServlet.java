package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("jsp/userEdit.jsp");
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
			int id =  Integer.parseInt(request.getParameter("id"));
			
			User user = new User();
			user.editUser(name, nickname, password, id);
			
			HttpSession session = request.getSession(true);
	        session.setAttribute("user", user);
			
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/jsp/userEditDone.jsp");
			dispatcher.forward(request, response);
	}

}

