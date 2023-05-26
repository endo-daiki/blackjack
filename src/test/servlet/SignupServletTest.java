package servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Insert;
import login.Login;
import login.UserDelete;
import model.User;

class SignupServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static SignupServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new SignupServlet();
	}
	
	@Test
	public void testDoPost() 
			throws ServletException, IOException {
		request.setParameter("id", "testId");
		request.setParameter("name", "testName");
		request.setParameter("password", "password");
		request.setParameter("checkPassword", "password");
		
		servlet.doPost(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("signupDone.jsp", url);
	}
		
	@AfterAll
	public static void clean() {
		UserDelete.delete("testId", request);
	}

}
