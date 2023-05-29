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

import database.Delete;
import database.Insert;
import database.Select;
import model.User;

class UserEditTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User("testId", "testName", "password", "password");
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
       
       user = new User("otherId", "otherName", "password", "password");
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
    }

	@Test 
	public void testIdValidation() 
			throws ServletException, IOException{ //idが未入力のときにエラーを返すかどうか
		
		User user = new User("testId", "", "testName", "password", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("IDを入力してください", error_msg);
	}
	
	@Test 
	public void testNameValidation() 
			throws ServletException, IOException{ //名前が未入力のときにエラーを返すかどうか
		
		User user = new User("testId", "newId", "", "password", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("名前を入力してください", error_msg);
	}
	
	@Test 
	public void testPasswordValidation() 
			throws ServletException, IOException{ //パスワードが未入力のときにエラーを返すかどうか
		
		User user = new User("testId", "newId", "newTestName", "", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードを入力してください", error_msg);
	}
	
	@Test 
	public void testCheckPasswordValidation() 
			throws ServletException, IOException{ //パスワードと確認用パスワードが違う(未入力含む)のときにエラーを返すかどうか
		
		User user = new User("testId", "newId", "newTestName", "PAssWorD", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("パスワードが正しくありません", error_msg);
	}
	
	@Test
	public void testDupricationId() 
			throws ServletException, IOException{ //登録したいidの重複判定のテスト
		
		User user = new User("testId", "otherId", "newTestName", "password", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);
		
		dispatcher.forward(request, response);	
		
		String error_msg = (String)request.getAttribute("error_msg"); //エラーメッセージを取得
		assertEquals("このIDは既に使われています。", error_msg);
	}
	
	@Test
	public void testExcute()
		throws ServletException, IOException{ //正しくユーザー登録されているかどうかのテスト
		
		User user = new User("testId", "testId", "変更したユーザー名", "password", "password");
		RequestDispatcher dispatcher = UserEdit.excute(user, request);		//ユーザーを登録
		
		dispatcher.forward(request, response);	
		
		User updateUser = Select.selectUser(user.getId(), user.getPassword()); //dbからユーザーを取得
		assertEquals("変更したユーザー名", updateUser.getName()); //取得したユーザーの名前を確認
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteUser("otherId");
	}
}
