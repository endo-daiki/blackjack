package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import database.Delete;
import database.Insert;
import database.Select;
import database.Update;
import model.User;
import model.playLog;

class SelectTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	static User user = new User();
	
	@BeforeAll
	public static void setup() {
		for(int i = 0; i < 5; i++) { //適当なユーザーを作成
			Insert.insertUser(String.valueOf(i),  "Name" + i, "password");
			Update.updateResult(String.valueOf(i), "win");
			Insert.insertLog(String.valueOf(i), "win");
			Update.updateResult(String.valueOf(i), "draw");
			Insert.insertLog(String.valueOf(i), "draw");
			Update.updateResult(String.valueOf(i), "lose");
			Insert.insertLog(String.valueOf(i), "lose");
		}
		Insert.insertUser(String.valueOf(6),  "Name6", "password");
		Update.updateResult(String.valueOf(6), "win");
		Insert.insertLog(String.valueOf(6), "win");
		
		user = Select.selectUser("0", "password"); //作ったユーザーでログイン
		
		session.setAttribute("user", user);
		request.setSession(session);
	}
		
	@Test 
	public void testSelectId() {
		boolean idCheck = Select.selectId("0");
		assertEquals(true, idCheck);
	}
	
	@Test
	public void testSelectUser() {
		User user = Select.selectUser("0", "password");
		assertEquals("Name0", user.getName());
	}
	
	@Test
	public void testSelectRanker() {
		List<User> ranker = Select.selectRanker();
		User topRanker = ranker.get(0);
		
		assertEquals(1, topRanker.getRate());
		assertEquals(5, ranker.size());
	}
		
	@Test
	public void  testSelectPlayLog() {
		List<playLog> playLogs = Select.selectPlayLog("6");
		playLog log = playLogs.get(0);
		
		assertEquals("win", log.getLog());
	}
	
	@AfterAll
	public static void clean() {
		for(int i = 0; i < 5; i++) {
			Delete.deleteUser(String.valueOf(i));
			Delete.deleteLog(String.valueOf(i));
		}
		Delete.deleteUser(String.valueOf(6));
		Delete.deleteLog(String.valueOf(6));
		session.invalidate();
	}

}
