package servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Insert;
import login.Login;
import model.User;

class UserDeleteServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static UserDeleteServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new UserDeleteServlet();
		
		 user = new User("testId", "testName", "password", "password");
		 
		 Insert insert = new Insert();
	     insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.userLogin(user, request);
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("userDeleteCheck.jsp", url);
	}
	
	@Test
	public void testDoPost()
		throws ServletException, IOException {
		
		request.setParameter("id", "testId");
		
		servlet.doPost(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("userDeleteDone.jsp", url);
	}

}
