package gameSystem;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;

import model.Card;
import model.Game;
import model.User;

public class Blackjack {
	public static Game game = null;
	private static boolean finished = false;
	private static final String[] suit = {"spade","heart","diamond","club"};
    private static final String[] no = {"1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    private static Card draw() {
    	List<Card> deck = new ArrayList<Card>();
    	deck = game.getDeck();
    	
    	Card card = deck.get(0);
    	deck.remove(0);
    	
    	game.setDeck(deck);
    	
    	return card;
    }
    
    private static int pointCalc(List<Card> hand) {
    	int point = 0;
    	
    	for(Card card : hand) {
    		if( card.no.equals("j") || card.no.equals("q") || card.no.equals("k") ) {
    			point += 10;
    		} else {
    			point += Integer.parseInt(card.no);
    		}
    	}
    	return point;
    }
    
    public static RequestDispatcher getGame(HttpServletRequest request) {
    	request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("playerTurn.jsp");
		
		return dispatcher;
    }
    
    public static RequestDispatcher getResult(HttpServletRequest request) {
    	if(game == null) {
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("main.jsp");
    		
    		return dispatcher;
    	}
    	
    	request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("result.jsp");
		
		return dispatcher;
    }
	
	public static String Setup(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");        
        if(user == null) {
        	return "/blackjack";
        }
        
        String url = "PlayerTurn";
		game = new Game(user.getId());
		List<Card> deck = new ArrayList<Card>();
		
		finished = false;

		for(String suit : suit) {
			for(String no : no) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
		game.setDeck(deck);
		
		List<Card> hand = new ArrayList<Card>();
		hand.add(draw());
		hand.add(draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(pointCalc(hand));
		
		List<Card> dealerHand = new ArrayList<Card>();
		dealerHand.add(draw());
		dealerHand.add(draw());
		
		game.setDealerHand(dealerHand);
		game.setDealerPoint(pointCalc(dealerHand));
		
		return url;
		
	}
	
	public static String Hit(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("user") == null || game == null) {
			return "/blackjack";
		}

		List<Card> hand = game.getPlayerHand();
		String url = "PlayerTurn";
		
		if(finished == true) {
			return "Result";
		}
		
		hand.add(draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(pointCalc(hand));
		
		if(pointCalc(hand) > 21) {
			game.setPlayerBurst(true);
			
			url = Stand(request);
		} else if(pointCalc(hand) == 21) {
			url = Stand(request);
		}
		
		return url;
	}
	
	public static String Stand(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if(finished == true || session.getAttribute("user") == null || game == null) {
			return "Result";
		}
		
		String url = "Result";
		
		if(game.getPlayerBurst()) {
			game.setResult("lose");
			
			Database.insertLog(game.getUserId(), game.getResult());
			User updateUser = Database.updateResult(game.getUserId(), game.getResult());
			
		    session.setAttribute("user", updateUser);
			
			request.setAttribute("game", game);
			finished = true;
			
		} else {
			int i = game.getDealerPoint();
			while(i < 17) {
				List<Card> dealerHand = game.getDealerHand();
				dealerHand.add(draw());
				
				game.setDealerHand(dealerHand);
				game.setDealerPoint(pointCalc(dealerHand));
				
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
			
			finished = true;
			Database.insertLog(game.getUserId(), game.getResult());
			User updateUser = Database.updateResult(game.getUserId(), game.getResult());

		    session.setAttribute("user", updateUser);
			
		}
		
		return url;
	}

	public static void resetGame() {
		game = null;
	}
}
