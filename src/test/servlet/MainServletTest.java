package servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Insert;
import login.Login;
import login.UserDelete;
import model.User;

class MainServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse  response = new MockHttpServletResponse();
	static MainServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new MainServlet();
		
		 user = new User("testId", "testName", "password", "password");
		 
		 Insert insert = new Insert();
	     insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.userLogin(user, request);
	}
	
	@BeforeEach
	public void setup2() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("./", url);
	}
	
	@Test
	public void testDoPost() 
			throws ServletException, IOException {
		
		servlet.doPost(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("./", url);
	}
	
	@AfterAll
	public static void clean() {
		UserDelete.excute("testId", request);
	}
}
