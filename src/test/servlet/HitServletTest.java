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

class HitServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static HitServlet servlet;
	static User user;
	
	@BeforeAll
	public static void setup() {
		servlet = new HitServlet();
		
		 user = new User("testId", "testName", "password", "password");
	     Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	     Login.login(user, request);
	}
	
	@Test
	public void testDoget() throws
		ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("playerTurn.jsp", url);
	}
	
	@Test
	public void testDoPost() throws
		ServletException, IOException {
		
		response = new MockHttpServletResponse();
		
		new Blackjack("testId");
		
		servlet.doPost(request, response);
		
		String url = response.getRedirectedUrl();
		assertEquals("PlayerTurn", url);
	}
	
	@AfterAll
	public static void clean() {
		UserDelete.delete("testId", request);
	}

}
