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
	        
		User user = Select.selectUser(id, password); //最初に登録したユーザーをdbから取得	
		assertEquals("newName", user.getName());
		assertEquals(true, checker);
	}
	
	@Test
	public void testUpdateResult() 
			throws ServletException, IOException{ //正しくゲームログが追加されているかどうか
		
		boolean checker = Update.updateResult(id, "win"); //勝ち数を1追加
		User user = Select.selectUser(id, password); //ユーザー情報を取得
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
		assertEquals(1, user.getWin()); //勝ち数が1になっているか確認
		
		checker = Update.updateResult(id, "lose"); //負け数を1追加
		user = Select.selectUser(id, password); //ユーザー情報を取得
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
		assertEquals(1, user.getLose()); //負け数が1になっているか確認
		
		checker = Update.updateResult(id, "draw"); //引き分け数を1追加
		user = Select.selectUser(id, password); //ユーザー情報を取得
		assertEquals(true, checker); //正しく編集(機能がうごいているか)されているかの確認
		assertEquals(1, user.getDraw()); //引き分け数が1になっているか確認
	}
	
	@AfterAll
	public static void clean() {
		Delete.deleteUser(id);
		Delete.deleteLog(id);
	}
}
