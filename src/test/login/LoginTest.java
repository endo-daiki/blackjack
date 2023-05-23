package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Delete;
import database.Insert;
import login.Login;
import model.User;

class LoginTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
		
	@BeforeAll
	 public static void setup() { //テスト用のユーザーを登録
		User user = new User("testId", "testName", "password", "password");
	    Insert.insertUser(user.getId(), user.getName(), user.getPassword());
	}
	
	@Test
	public void testLogin() throws Exception {
		User user = new User("testId", "password");
		RequestDispatcher dispatcher = Login.login(user, request);

		dispatcher.forward(request, response);	
				
		HttpSession session = request.getSession(true); //ログインが成功したとき、ユーザーデータはsessionに保存
		user = (User) session.getAttribute("user");
		assertEquals("testName", user.getName());
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
		
		User user = new User("testId", "");
		RequestDispatcher dispatcher = Login.login(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードを入力してください", error_msg);
	}
	
	@Test
	public void testInputCheck() throws 
		ServletException, IOException { //登録されたIdとpasswordが異なっているときのチェック
		
		User user = new User("testId", "PASSWORD");
		RequestDispatcher dispatcher = Login.login(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("アカウントが存在しないか、IDまたはパスワードが間違っています", error_msg);
	}
		
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
	}
}
