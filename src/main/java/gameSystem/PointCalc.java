package gameSystem;

import java.util.List;

import model.Game;

public class PointCalc {
	public static int Calc(List<Card> hand) {
		int point = 0;
		Game game = Blackjack.game;
		game.setAce(false);
    	
    	for(Card card : hand) {
    		if( card.no.equals("1")) {
    			point += 11;
    			game.setAce(true);
    		} else 
    		if( card.no.equals("j") || card.no.equals("q") || card.no.equals("k") ) {
    			point += 10;
    		} else {
    			point += Integer.parseInt(card.no);
    		}
    	}
    	
    	if(game.getAce() && point > 21) {
    		game.setAce(false);
    		point -= 10;
    	}
    	
    	return point;
	}
}
