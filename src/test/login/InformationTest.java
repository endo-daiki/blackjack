package login;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

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

class InformationTest {
	static MockHttpServletRequest request = new MockHttpServletRequest();
	static MockHttpServletResponse response = new MockHttpServletResponse();
	static MockHttpSession session = new MockHttpSession();
	
	static User user;
	
	@BeforeAll
	public static void setup() {
		for(int i = 0; i < 5; i++) { //適当なユーザーを作成
			Insert.insertUser(String.valueOf(i),  "Name" + i, "password");
			Update.updateResult(String.valueOf(i), i * 10);
			Insert.insertLog(String.valueOf(i), i * 10);
		}
		user = Select.selectUser("0", "password"); //作ったユーザーでログイン
		
		session.setAttribute("user", user);
		request.setSession(session);
	}
	
	@Test
	public void testPlayInfo() throws 
			ServletException, IOException{ //ログインされたユーザーの情報を取得
		
		String url = Information.getPlayInfo(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				
		
		dispatcher.forward(request, response);
		
		User userInfo = (User)request.getAttribute("user"); //ログインしているユーザーの情報
		List<User> ranker = (List<User>)request.getAttribute("ranker"); //勝率の上位5名を選出
		List<playLog> playLog = (List<playLog>)request.getAttribute("playLog"); //ゲームプレイした記録を取得
		
		assertEquals("Name0", userInfo.getName());
		assertEquals(5, ranker.size());
		assertEquals("0", playLog.get(0).getUserId());
	}
	
	@Test
	public void testUserInfo() throws 
			ServletException, IOException{ //ログインされたユーザーの情報を取得
		
		String url = Information.getUserInfo(request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				
		dispatcher.forward(request, response);
		
		User userInfo = (User)request.getAttribute("user"); //ログインしているユーザーの情報
		
		assertEquals("Name0", userInfo.getName());
	}
	
	@AfterAll
	public static void clean() {
		for(int i = 0; i < 5; i++) {
			Delete.deleteUser(String.valueOf(i));
			Delete.deleteLog(String.valueOf(i));
		}
		session.invalidate();
	}

}
