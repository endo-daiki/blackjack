package login;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import database.Select;
import model.User;
import userAdmin.Signup;

class SignupTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User("testId", "testName", "password", "password");
       
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
       request.setSession(session);
    }

	@Test 
	public void testIdValidation() 
			throws ServletException, IOException{ //idが未入力のときにエラーを返すかどうか
		
		User user = new User("", "testName", "password", "password");
		
		String url = Signup.userSignup(user, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("IDを入力してください", error_msg);
	}
	
	@Test 
	public void testNameValidation() 
			throws ServletException, IOException{ //名前が未入力のときにエラーを返すかどうか
		
		User user = new User("testmail@mail.com", "", "password", "password");
		
		String url = Signup.userSignup(user, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("名前を入力してください", error_msg);
	}
	
	@Test 
	public void testPasswordValidation() 
			throws ServletException, IOException{ //パスワードが未入力のときにエラーを返すかどうか
		
		User user = new User("testmail@mail.com", "testName", "", "password");
		
		String url = Signup.userSignup(user, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードを入力してください", error_msg);
	}
	
	@Test 
	public void testCheckPasswordValidation() 
			throws ServletException, IOException{ //パスワードと確認用パスワードが違う(未入力含む)のときにエラーを返すかどうか
		
		User user = new User("testmail@mail.com", "testName", "AbcD1234", "abcd1234");
		
		String url = Signup.userSignup(user, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードが正しくありません", error_msg);
	}
	
	@Test
	public void testDupricationId() 
			throws ServletException, IOException{ //登録したいidの重複判定のテスト
		
		User user = new User("testId", "テストネーム", "PASSWORD", "PASSWORD");
		
		String url = Signup.userSignup(user, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("このIDは既に使われています。", error_msg);
	}
	
	@Test
	public void signupTest()
		throws ServletException, IOException{ //正しくユーザー登録されているかどうかのテスト
		
		User user = new User("newUser", "新しいユーザー", "PASSWORD", "PASSWORD");//正しい入力値のユーザー
		
		String url = Signup.userSignup(user, request);//ユーザーを登録
		RequestDispatcher dispatcher = 	request.getRequestDispatcher(url);
		
		dispatcher.forward(request, response);	
		
		User loginUser = Select.selectUser(user.getId(), user.getPassword()); //dbからユーザーを取得
		assertEquals("新しいユーザー", loginUser.getName()); //取得したユーザーの名前を確認
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteUser("newUser");
	}
}
