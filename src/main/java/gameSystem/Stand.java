package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import model.Game;
import model.User;

public class Stand {
	public static String url;
	private static HttpSession session;
	private static Game game;
	private static Deck deck;
	private static HttpServletRequest request;
	
	public Stand(HttpServletRequest request) {
		this.request = request;
		session = request.getSession(true);
		game = Blackjack.game;
		deck = Blackjack.deck;
		url = "Result";
	}
	
	public static String getUrl() {
		if(game == null || session.getAttribute("user") == null) {
			url = "/blackjack";
			return url;
		}
		if(game.getFinished() == true) {
			return url;
		}
		
		if(game.getPlayerBurst()) {
			game.setDealerPoint(PointCalc.Calc(game.getDealerHand()));
			game.setResult("lose");
			
			playLog();
			
			request.setAttribute("game", game);
			game.setFinished(true);
			
			return url;
		} 
		
		dealerTurn();
		gameResult();
		playLog();
		
		request.setAttribute("game", game);		
		
		return url;
	}
	
	private static void playLog() {
		game.setFinished(true);
		
		new Update();
		Update.updateResult(game.getUserId(), game.getResult());
		
		new Insert();
		Insert.insertLog(game.getUserId(), game.getResult());
		
		new Select();
		User user = (User) session.getAttribute("user");
		user = Select.selectUser(user.getId(), user.getPassword());
		
		session.setAttribute("user", user);
		
		game.setFinished(true);
	}
	
	private static void dealerTurn() {
		game.setAce(false);
		game.setDealerPoint(PointCalc.Calc(game.getDealerHand()));
		int point = game.getDealerPoint();
		
		while(point < 17) {
			List<Card> dealerHand = game.getDealerHand();
			dealerHand.add(deck.Draw());
			
			game.setDealerHand(dealerHand);
			game.setDealerPoint(PointCalc.Calc(dealerHand));
			
			point = game.getDealerPoint();
			
			if(point > 21) {
				game.setDealerBurst(true);
			}
		}
	}
	
	private static void gameResult() {
		if(game.getDealerBurst() || game.getPlayerPoint() > game.getDealerPoint()) {
			game.setResult("win");
		} else if (game.getPlayerPoint() < game.getDealerPoint()) {
			game.setResult("lose");
		} else {
			game.setResult("draw");
		}
	}
}
