package sampleProject;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import login.Login;
import model.Card;
import model.User;

class sampleTest {

	@Test
	public void instantiate() {
		Card card = new Card("heart", "1");
		assertEquals("heart", card.suit);
		assertEquals("1", card.no);
	}
	
	@Test
	public void instantiate2() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		User user = new User();
		RequestDispatcher dispatcher = Login.login(user, request);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}		
		assertEquals("IDを入力してください", (String)request.getAttribute("error_msg"));
	}

}
