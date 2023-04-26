package gameSystem;

import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import model.Card;
import model.Game;

public class Blackjack {
	private List<Card> deck;
	private String[] suit = {"spade","heart","diamond","club"};
    private String[] no = {"1","2","3","4","5","6","7","8","9","10","j","q","k"};
    
    private Card draw() {
    	Card card = deck.get(0);
    	deck.remove(0);
    	
    	return card;
    }
    
    private int pointCalc(List<Card> hand) {
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
    
    public boolean burstCheck(int point) {
    	if(point > 21) {
    		return false;
    	}
    	return true;
    	//負け判定の画面を表示させる
    }
	
	public Game setup(Game game, HttpServletRequest request) {		
		deck = new ArrayList<Card>();
		
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
		
		return request;
	}
	
	public Game Hit(Game game) {
		List<Card> hand = game.getPlayerHand();
		hand.add(draw());
		
		game.setPlayerHand(hand);
		game.setPlayerPoint(pointCalc(hand));
		
		return game;
	}
	
	public Game Stand(Game game) {
		//playerがburstしていたら、dealerはドローしないで、結果を表示させる
		//17以下だったっらカードを引く
		return game;
	}
}
