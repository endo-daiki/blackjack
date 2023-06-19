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
import model.User;
import userAdmin.Login;

class StandServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static StandServlet servlet;
	static User user;
	
	@BeforeAll
	public static void setup() {
		servlet = new StandServlet();
		
		 user = new User("testId", "testName", "password", "password");
		 
		 Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.userLogin(user, request);
	}
	
	@Test
	public void testDoget() throws
		ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("result.jsp", url);
	}
	
	@Test
	public void testDoPost() throws
		ServletException, IOException {
		
		response = new MockHttpServletResponse();
		
		new Blackjack(10, "testId");
		request.setParameter("select", "PLAYING");
		servlet.doPost(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("Result", url);
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
	}
}
