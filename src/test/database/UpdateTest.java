package database;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import model.User;

class UpdateTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static String id = "testId";
	static String name = "testName";
	static String password = "password";
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User(id, name, password, password);
       
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
    }


	@Test 
	public void testUpdateUser() 
			throws ServletException, IOException{ //正しく編集されているかどうか
		
		boolean checker = Update.updateUser(id, id, "newName", password);
		assertEquals(true, checker);

		User updateUser = Select.selectUser(id, password);
		assertEquals("newName", updateUser.getName());
	}
	
	@Test
	public void testUpdateResult() 
			throws ServletException, IOException{ //正しくゲームログが追加されているかどうか
		
		boolean checker = Update.updateResult(id, 10); //取得数10を追加
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認

		User updateUser = Select.selectUser(id, password);
		assertEquals(110, updateUser.getTip());
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser("testId");
		Delete.deleteLog("testId");
	}
}
