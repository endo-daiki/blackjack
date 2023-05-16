package gameSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import login.Login;
import model.User;

public class Setup {
	private static String url;
	private static HttpSession session;
	private static HttpServletRequest request;
	
	public Setup(HttpServletRequest request) { 
		this.request = request;
		session = request.getSession(true);
		User user = (User)session.getAttribute("user"); 
		new Blackjack(user.getId());

		url = "PlayerTurn";
	}
	
	public static String getUrl() {		
        if(Login.loginCheck(request) == false) {
        	url = "/blackjack";
        	return url;
        }
        
        Blackjack.dealerDraw();
        Blackjack.dealerDraw();
		
		Blackjack.playerDraw();
		Blackjack.playerDraw();
		
		if(Blackjack.game.getPlayerPoint() == 21) {
			new Stand(request);
			url = Stand.getUrl();
			
			return url;
		}
		
		return url;
	}
}
