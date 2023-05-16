package gameSystem;

import java.util.List;

public class PointCalc {
	public static int Calc(List<Card> hand) {
		int point = 0;
		Blackjack.game.setAce(false);
		int aceCount = 0;
    	
    	for(Card card : hand) {
    		if(card.no.equals("1")) {
    			point += 11;
    			aceCount++;
    			Blackjack.game.setAce(true);
    		} else if(card.no.equals("j") || card.no.equals("q") || card.no.equals("k") ) {
    			point += 10;
    		} else {
    			point += Integer.parseInt(card.no);
    		}
    	}

    	if(Blackjack.game.getAce() && point > 21) {
    		Blackjack.game.setAce(false);
    		for(int i = 0; i < aceCount; i++) {
    			if(point > 21) {
    				point -= 10;    				    					
    			}
    		}
    	}
    	return point;
	}
}
