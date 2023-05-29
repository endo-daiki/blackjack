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

class UserEditServletTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static UserEditServlet servlet;
	static User user;

	@BeforeAll
	public static void setup() {
		servlet = new UserEditServlet();
		
		user = new User("testId", "testName", "password", "password");
	    Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	     
	    Login.userLogin(user, request);
	}
	
	@Test
	public void testDoGet()
		throws ServletException, IOException {
		
		servlet.doGet(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("userEdit.jsp", url);
	}
	
	@Test
	public void testDoPost()
		throws ServletException, IOException {
		
		request.setParameter("oldId", "testId");
		request.setParameter("newId", "testId");
		request.setParameter("name", "newName");
		request.setParameter("password", "password");
		request.setParameter("checkPassword", "password");

		servlet.doPost(request, response);
		
		String url = response.getForwardedUrl();
		assertEquals("userEditDone.jsp", url);
	}
	
	@AfterAll
	public static void clean() {
		UserDelete.excute("testId", request);
	}

}
