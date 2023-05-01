package gameSystem;

import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Database;

import java.util.ArrayList;

import model.Card;
import model.Game;
import model.User;

public class Blackjack {
	private static Game game;
	private static final List<Card> deck = new ArrayList<Card>();
	private static final String[] suit = {"spade","heart","diamond","club"};
    private static final String[] no = {"1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    public static Card draw() {
    	Card card = deck.get(0);
    	deck.remove(0);
    	
    	return card;
    }
    
    public static int pointCalc(List<Card> hand) {
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
    
    public static boolean burstCheck(int point) {
    	if(point > 21) {
    		return false;
    	}
    	return true;
    	//負け判定の画面を表示させる
    }
	
	public static RequestDispatcher setup(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
        User user = (User)session.getAttribute("user");
        
		game = new Game(user.getId());
		
		for(String suit : suit) {
			for(String no : no) {
				Card card = new Card(suit, no);
				deck.add(card);
			}
		}
		
		Collections.shuffle(deck);
		
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

		request.setAttribute("game", game);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("playerTurn.jsp");
		
		return dispatcher;
	}
	
	public static RequestDispatcher Hit(HttpServletRequest request) {
		List<Card> hand = game.getPlayerHand();
		hand.add(draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(pointCalc(hand));
		
		if(pointCalc(hand) > 21) {
			game.setPlayerBurst(true);
			
			request.setAttribute("game", game);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("Stand");
			
			return dispatcher;
		} else if(pointCalc(hand) == 21) {
			request.setAttribute("game", game);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("Stand");
			
			return dispatcher;
		}
		
		request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("playerTurn.jsp");
		
		return dispatcher;
	}
	
	public static RequestDispatcher Stand(HttpServletRequest request) {
		
		if(game.getPlayerBurst()) {
			game.setResult("lose");
			
			request.setAttribute("game", game);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("result.jsp");
			
			Database.updateResult(game.getUserId(), game.getResult());
			
			return dispatcher;
		}
		
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
		
		Database.updateResult(game.getUserId(), game.getResult());
		
		request.setAttribute("game", game);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("result.jsp");
		
		return dispatcher;
	}
}
