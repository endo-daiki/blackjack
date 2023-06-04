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
import gameSystem.Blackjack;
import login.Login;
import login.UserDelete;
import model.User;

class ResultServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static ResultServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new ResultServlet();
		
		 user = new User("testId", "testName", "password", "password");
		 
		 Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.userLogin(user, request);
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		new Blackjack(10);
		Blackjack.Stand();
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("result.jsp", url);
	}
	
	@AfterAll
	public static void clean() {
		UserDelete.excute("testId", request);
	}

}
