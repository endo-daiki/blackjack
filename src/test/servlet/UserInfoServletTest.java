package servlet;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Delete;
import database.Insert;
import login.Login;
import model.User;

class UserInfoServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static UserInfoServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new UserInfoServlet();
		
		user = new User("testId", "testName", "password", "password");
		
		
		Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	    Login.userLogin(user, request);
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("userInfo.jsp", url);
	}
		
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
	}
}
