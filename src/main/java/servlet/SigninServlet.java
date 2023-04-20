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
		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e){
//			throw new IllegalStateException("");
//		}
//		
		Connection con = null;
		try {
			 con = DriverManager.getConnection(
				      "jdbc:mysql://localhost:3306/blackjack",
				      "root",
				      ""
				    );
			 
////			 PreparedStatement pstmt = con.prepareStatement
////					 ("insert into user (name, nickname, password) values (?,?,?)");
////			 
////			 pstmt.setString(1, name);
////			 pstmt.setString(2, nickname);
////			 pstmt.setString(3, password);
////			 ResultSet rs = pstmt.executeQuery();
//			 
//			 rs.close();
//			 pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		User user = new User();
//		user.insertUser(name, nickname, password);
//		
//		RequestDispatcher dispatcher = 
//				request.getRequestDispatcher("jsp/signinDone.jsp");
//		dispatcher.forward(request, response);
	}

}
