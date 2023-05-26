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
import model.User;

class LoginServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static LoginServlet servlet;
	
	@BeforeAll
	public static void setup() {
		servlet = new LoginServlet();
		
		 User user = new User("testId", "testName", "password", "password");
	     Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	}
	
	@Test
	public void testDoget() throws
		ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("/login.jsp", url);
	}
	
	@Test
	public void testDoPost() throws
		ServletException, IOException {
		
		request.setParameter("id", "butId");
		request.setParameter("password", "butPassword");
		
		servlet.doPost(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("/login.jsp", url);
		assertEquals("アカウントが存在しないか、IDまたはパスワードが間違っています", request.getAttribute("error_msg"));
		
		request.setParameter("id", "testId");
		request.setParameter("password", "password");
		
		servlet.doPost(request, response);
		
		url = response.getForwardedUrl();
		assertEquals("/Main", url);		
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
	}

}
