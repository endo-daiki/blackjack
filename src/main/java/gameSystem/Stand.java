package gameSystem;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import model.Card;
import model.Game;
import model.User;

public class Stand {
public static String url;
	
	public Stand(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Game game = Blackjack.game;
		if(game.getFinished() == true || session.getAttribute("user") == null || game == null) {
			url = "Result";
			return;
		}
		
		url = "Result";
		game.setAce(false);
		game.setDealerPoint(PointCalc.Calc(game.getDealerHand()));
		Deck deck = Blackjack.deck;
		
		
		if(game.getPlayerBurst()) {
			game.setResult("lose");
			
			new Update();
			Update.updateResult(game.getUserId(), game.getResult());
			
			new Insert();
			Insert.insertLog(game.getUserId(), game.getResult());
			
			new Select();
			User user = (User) session.getAttribute("user");
			user = Select.selectUser(user.getId(), user.getPassword());
			
			request.setAttribute("game", game);
			game.setFinished(true);
			
		} else {
			int i = game.getDealerPoint();
			while(i < 17) {
				List<Card> dealerHand = game.getDealerHand();
				dealerHand.add(deck.Draw());
				
				game.setDealerHand(dealerHand);
				game.setDealerPoint(PointCalc.Calc(dealerHand));
				
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
			new Update();
			Update.updateResult(game.getUserId(), game.getResult());
			
			new Insert();
			Insert.insertLog(game.getUserId(), game.getResult());
			
			new Select();
			User user = (User) session.getAttribute("user");
			user = Select.selectUser(user.getId(), user.getPassword());

		    session.setAttribute("user", user);
			
		}
	}
	
	public static String getUrl() {
		return url;
	}
}
