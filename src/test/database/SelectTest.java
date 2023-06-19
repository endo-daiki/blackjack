package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.User;
import model.playLog;

class SelectTest {
	static User user = new User();
	
	@BeforeAll
	public static void setup() {
		for(int i = 0; i < 5; i++) { //適当なユーザーを作成
			Insert.insertUser(String.valueOf(i),  "Name" + i, "password");
			Update.updateResult(String.valueOf(i), i * 10);
			Insert.insertLog(String.valueOf(i), i * 10);
		}
		Insert.insertUser(String.valueOf(6),  "Name6", "password");
		Update.updateResult(String.valueOf(6), 10000);
		Insert.insertLog(String.valueOf(6), 10000);
		
		user = Select.selectUser("0", "password"); //作ったユーザーでログイン
	}
		
	@Test 
	public void testSelectId() { //idが使われているか(存在しているか)の確認
		boolean idCheck = Select.selectId("0");
		assertEquals(true, idCheck);
	}
	
	@Test
	public void testSelectUser() {
		User user = Select.selectUser("0", "password");
		assertEquals("Name0", user.getName());
	}
	
	@Test
	public void testSelectRanker() { //上位５人を取得する
		List<User> ranker = Select.selectRanker();
		User topRanker = ranker.get(0);
		
		assertEquals(10100, topRanker.getTip());
		assertEquals(5, ranker.size());
	}
		
	@Test
	public void  testSelectPlayLog() {
		List<playLog> playLogs = Select.selectPlayLog("6");
		playLog log = playLogs.get(0);
		
		assertEquals("10000", log.getLog());
		assertEquals(1, playLogs.size());
	}
	
	@AfterAll
	public static void clean() {
		for(int i = 0; i < 5; i++) {
			Delete.deleteUser(String.valueOf(i));
			Delete.deleteLog(String.valueOf(i));
		}
		Delete.deleteUser(String.valueOf(6));
		Delete.deleteLog(String.valueOf(6));
	}

}
