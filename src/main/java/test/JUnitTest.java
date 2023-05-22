package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.http.HttpSession;

import login.Login;
import model.Card;
import model.User;

class JUnitTest {
	MockHttpServletRequest request = new MockHttpServletRequest();
	MockHttpServletResponse response = new MockHttpServletResponse();
		
	@BeforeEach
	public void dbInsert() {
		
	}
	
	@Test
	public void testLogin() throws Exception {
		User user = new User("test@mail.com", "password");
		RequestDispatcher dispatcher = Login.login(user, request);

		dispatcher.forward(request, response);	
				
		HttpSession session = request.getSession(true); //ログインが成功したとき、ユーザーデータはsessionに保存
		user = (User) session.getAttribute("user");
		assertEquals("遠藤大基", user.getName());
	}
	
	@Test
	public void testIdCheck() throws 
		ServletException, IOException { //ログインの際、Idが入力されていない時の動作チェック
		
		User user = new User("", "password");
		RequestDispatcher dispatcher = Login.login(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("IDを入力してください", error_msg);
	}

	@Test
	public void testPasswordCheck() throws 
		ServletException, IOException { //ログインの際、passwordが入力されていない時の動作チェック
		
		User user = new User("test@mail.com", "");
		RequestDispatcher dispatcher = Login.login(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードを入力してください", error_msg);
	}
	
	@Test
	public void testInputCheck() throws 
		ServletException, IOException { //登録されたIdとpasswordが異なっているときのチェック
		
		User user = new User("test@mail.com", "PASSWORD");
		RequestDispatcher dispatcher = Login.login(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("アカウントが存在しないか、IDまたはパスワードが間違っています", error_msg);
	}
	
}
