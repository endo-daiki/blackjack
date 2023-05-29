package login;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Delete;
import database.Insert;
import model.User;

class LogoutTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static String id = "testId";
	static String name = "testName";
	static String password = "password";
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User(id, name, password, password);
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
       
       Login.userLogin(user, request);
    }

	@Test 
	public void testUserLogout() { //正しくログアウト(セッションから削除されているか)
		
		Logout.userLogout(request); 
	        
		HttpSession session = request.getSession(true); 
		User user = (User)session.getAttribute("uesr"); //セッションからユーザー登録情報を取得
		assertNull(user);
	}

	@AfterAll
	public static void clean() {
		Delete.deleteUser(id);
	}
}
