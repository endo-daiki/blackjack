package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import model.User;
import model.playLog;

class SelectTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	static Select select = new Select();
	
	static User user = new User();
	
	@BeforeAll
	public static void setup() {
		Insert insert = new Insert();
		Update update = new Update();
		
		for(int i = 0; i < 5; i++) { //適当なユーザーを作成
			insert.insertUser(String.valueOf(i),  "Name" + i, "password");
			update.updateResult(String.valueOf(i), i * 10);
			insert.insertLog(String.valueOf(i), i * 10);
		}
		insert.insertUser(String.valueOf(6),  "Name6", "password");
		update.updateResult(String.valueOf(6), 100);
		insert.insertLog(String.valueOf(6), 100);
		
		user = select.selectUser("0", "password"); //作ったユーザーでログイン
		
		session.setAttribute("user", user);
		request.setSession(session);
	}
		
	@Test 
	public void testSelectId() {
		boolean idCheck = select.selectId("0");
		assertEquals(true, idCheck);
	}
	
	@Test
	public void testSelectUser() {
		User user = select.selectUser("0", "password");
		assertEquals("Name0", user.getName());
	}
	
	@Test
	public void testSelectRanker() {
		List<User> ranker = select.selectRanker();
		User topRanker = ranker.get(0);
		
		assertEquals(200, topRanker.getTip());
		assertEquals(5, ranker.size());
	}
		
	@Test
	public void  testSelectPlayLog() {
		List<playLog> playLogs = select.selectPlayLog("6");
		playLog log = playLogs.get(0);
		
		assertEquals("100", log.getLog());
	}
	
//	@AfterAll
//	public static void clean() {
//		Delete delete = new Delete(); 
//		for(int i = 0; i < 5; i++) {
//			delete.deleteUser(String.valueOf(i));
//			delete.deleteLog(String.valueOf(i));
//		}
//		delete.deleteUser(String.valueOf(6));
//		delete.deleteLog(String.valueOf(6));
//		session.invalidate();
//	}

}
