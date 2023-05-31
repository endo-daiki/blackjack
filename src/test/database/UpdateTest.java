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
	static Update update = new Update();
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User(id, name, password, password);
       
       Insert insert = new Insert();
       insert.insertUser(user.getId(), user.getName(), user.getPassword());
    }

	@Test 
	public void testUpdateUser() 
			throws ServletException, IOException{ //正しく編集されているかどうか
		
		boolean checker = update.updateUser(id, id, "newName", password);
		assertEquals(true, checker);
	}
	
	@Test
	public void testUpdateResult() 
			throws ServletException, IOException{ //正しくゲームログが追加されているかどうか
		
		boolean checker = update.updateResult(id, "win"); //勝ち数を1追加
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
		
		checker = update.updateResult(id, "lose"); //負け数を1追加
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
		
		checker = update.updateResult(id, "draw"); //引き分け数を1追加
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
	}
	
	@AfterAll
	public static void clean() {
		Delete delete = new Delete(); 
		delete.deleteUser("testId");
		delete.deleteLog("testId");
	}
}
