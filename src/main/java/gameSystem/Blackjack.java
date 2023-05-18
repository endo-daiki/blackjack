package gameSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Insert;
import database.Select;
import database.Update;
import model.Game;
import model.User;

public class Blackjack {
	public static Game game;
	private static String url;
    
    public Blackjack(String id) {
    	game = new Game(id);
    	url = "PlayerTurn";
    }

    public static RequestDispatcher getGame(HttpServletRequest request) {
    	RequestDispatcher dispatcher;
    	request.setAttribute("game", game);
    	if(!(game.getResult().equals("playing"))) {
    		Update.updateResult(game.getUserId(), game.getResult());
    		Insert.insertLog(game.getUserId(), game.getResult());
    		
    		HttpSession session = request.getSession(true);
    		
    		User user = (User)session.getAttribute("user");
    		User updateUser = Select.selectUser(user.getId(), user.getPassword());
    		session.setAttribute("user", updateUser);
    		
    		dispatcher = request.getRequestDispatcher("result.jsp");
    		return dispatcher;
    	}
		
    	dispatcher = request.getRequestDispatcher("playerTurn.jsp");
		return dispatcher;
    }
    
    public static String Setup() {    	
    	Deck deck = game.getDeck();
    	Hand hand = game.getPlayerHand();
    	Point point = game.getPlayerPoint();
    	
    	point.calc(hand.draw(deck));
    	point.calc(hand.draw(deck));
    	game.setPlayerHand(hand);
    	game.setPlayerPoint(point);
    	 	
    	hand = game.getDealerHand();
    	point = game.getDealerPoint();
    	
    	point.calc(hand.draw(deck));
    	point.calc(hand.draw(deck));
    	game.setDealerHand(hand);
    	game.setDealerPoint(point);
    	
    	game.setDeck(deck);
    	game.setResult("playing");
    	
    	if(game.getPlayerPoint().bjCheck()) {
			url = Stand();		
			return url;
		}
		
		return url;
    }
	
	public static String Hit() {
		Deck deck = game.getDeck();
		Hand playerHand = game.getPlayerHand();
    	Point playerPoint = game.getPlayerPoint();
    	
    	playerPoint.calc(playerHand.draw(deck));
    	
    	game.setDeck(deck);
    	game.setPlayerHand(playerHand);
    	game.setPlayerPoint(playerPoint);
		
    	if(playerPoint.burstCheck() || playerPoint.bjCheck()) {
			url = Stand();
			
			return url;
		}
    	
		return url;
	}
	public static String Stand() {
		Point playerPoint = game.getPlayerPoint();
		if(playerPoint.burstCheck()) {
			game.setResult("lose");
			return "Result";
		} 

		Deck deck = game.getDeck();
		Hand dealerHand = game.getDealerHand();
		Point dealerPoint = game.getDealerPoint();
		
		while(dealerPoint.point < 17) {
			dealerHand.draw(deck);
			dealerPoint.calc(dealerHand.draw(deck));
		}
		game.setDealerPoint(dealerPoint);
				
		if(dealerPoint.burstCheck() || playerPoint.point > dealerPoint.point) {
			game.setResult("win");
		} else if (playerPoint.burstCheck() || playerPoint.point < dealerPoint.point) {
			game.setResult("lose");
		} else {
			game.setResult("draw");
		}
		
		return "Result";
	}

	public static void resetGame() {
		game = null;
	}
}

