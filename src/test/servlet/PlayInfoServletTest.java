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
import gameSystem.Blackjack;
import login.Login;
import model.User;

class PlayInfoServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static PlayInfoServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new PlayInfoServlet();
		
		 user = new User("testId", "testName", "password", "password");
		 
		 Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.userLogin(user, request);
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		new Blackjack(10, "testId");
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("playInfo.jsp", url);
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
	}
}
