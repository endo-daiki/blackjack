package userAdmin;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import database.Select;
import model.User;

class UserDeleteTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	static String id = "testId";
	static String name = "testName";
	static String password = "password";
	
	@BeforeEach
    public void setup() { //テスト用のユーザーを先に登録
       User user = new User(id, name, password, password);
       
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
       
       request.setSession(session);
       Login.userLogin(user, request);
    }

	@Test 
	public void testExcute() 
			throws ServletException, IOException{ //正しく削除されているかどうか
		
		UserDelete.excute(id, request);

		User user = Select.selectUser(id, password); //最初に登録したユーザーをdbから取得(いなければnull)	
		assertNull(user);
	}
	
	@Test 
	public void testExcuteIdError() 
			throws ServletException, IOException{ //正しく削除されているかどうか
		try {
			UserDelete.excute("noId", request);
		} catch (IllegalArgumentException expected) {
		    assertEquals(expected.getMessage(), "Idが正しくありません。不正があります。");
		}
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser(id);
	}
	
}
