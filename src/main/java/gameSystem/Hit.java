package gameSystem;

import javax.servlet.http.HttpServletRequest;

import login.Login;
import model.Game;

public class Hit {
	private static String url;
	private static Game game;
	private static HttpServletRequest request;
	
	public Hit(HttpServletRequest request) {
		this.request = request;
		game = Blackjack.game;
		url = "PlayerTurn";
	}
	
	public static String getUrl() {
		if(Login.loginCheck(request) == false || game == null) {
			url = "/blackjack";
			return url;
		}
		if(game.getFinished() == true) {
			url = "Result";
			return url;
		}
		
		Blackjack.playerDraw();
		pointCheck(game.getPlayerPoint());
		
		return url;
	}
	
	private static void pointCheck(int point) {
		if(point == 21 || game.getPlayerBurst()) {			
			new Stand(request);
			url = Stand.getUrl();	
		}
	}
}
