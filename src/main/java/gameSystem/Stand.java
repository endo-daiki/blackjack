package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;
import model.Card;
import model.Game;
import model.User;

public class Stand {
public static String url;
	
	public Stand(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Game game = Blackjack.getGame();
		if(game.getFinished() == true || session.getAttribute("user") == null || game == null) {
			url = "Result";
		}
		
		url = "Result";
		game.setAce(false);
		game.setDealerPoint(PointCalc.pointCalc(game.getDealerHand()));
		Deck deck = new Deck(game.getDeck());
		
		if(game.getPlayerBurst()) {
			game.setResult("lose");
			
			User updateUser = Database.updateResult(game.getUserId(), game.getResult());
			Database.insertLog(game.getUserId(), game.getResult());
			
		    session.setAttribute("user", updateUser);
			
			request.setAttribute("game", game);
			game.setFinished(true);
			
		} else {
			int i = game.getDealerPoint();
			while(i < 17) {
				List<Card> dealerHand = game.getDealerHand();
				dealerHand.add(deck.Draw());
				
				game.setDealerHand(dealerHand);
				game.setDealerPoint(PointCalc.pointCalc(dealerHand));
				
				i = game.getDealerPoint();
				
				if(i > 21) {
					game.setDealerBurst(true);
				}
			}
			
			if(game.getDealerBurst() == true || game.getPlayerPoint() > game.getDealerPoint()) {
				game.setResult("win");
			} else if (game.getPlayerPoint() < game.getDealerPoint()) {
				game.setResult("lose");
			} else {
				game.setResult("draw");
			}
			
			game.setFinished(true);
			User updateUser = Database.updateResult(game.getUserId(), game.getResult());
			Database.insertLog(game.getUserId(), game.getResult());

		    session.setAttribute("user", updateUser);
			
		}
		
		game.setDeck(deck.getDeck());
	}
	
	public static String getUrl() {
		return url;
	}
}
