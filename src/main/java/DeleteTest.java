import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import database.Delete;
import database.Insert;
import database.Select;
import model.User;
import model.playLog;

class DeleteTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static String id = "testId";
	static String name = "testName";
	static String password = "password";
	
	@BeforeAll
    public static void setup() { //テスト用のユーザーを先に登録
       User user = new User(id, name, password, password);
       Insert.insertUser(user.getId(), user.getName(), user.getPassword());
       Insert.insertLog(id, "win");
    }
	
	@Test
	public void testDeleteUser()
			throws ServletException, IOException{ //ユーザー情報が正しく削除されているか
		
		boolean deleteChecker = Delete.deleteUser(id);
		User user = Select.selectUser(id, password); //ユーザーを取得(nullが取得できるはず)
	
		assertEquals(true, deleteChecker);
		assertNull(user);
	}
	
	@Test
	public void testDeleteLog()
			throws ServletException, IOException{ //ゲームログ情報が正しく削除されているかどうか
		
		boolean deleteChecker = Delete.deleteLog(id);
		List<playLog> playLog = Select.selectPlayLog(id); //ゲームログ情報を取得(nullが取得されるはず)
	
		assertEquals(true, deleteChecker);
		assertNull(playLog);
	}

}
