package login;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

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
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
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
	
}
