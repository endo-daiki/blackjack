package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BetTest {
    static Bet bet;

    @BeforeEach
    public void setup() { //ベットクラスを新規作成
        bet = new Bet(10);
    }

	@Test
    public void testBet() { //初期値が正しいか確認
        assertEquals(10, bet.getTip());    
        assertEquals(0, bet.refund());  
    }

    @Test
    public void testJudge() {
    	Player player = new Player();
    	Player dealer = new Player();
    	Card aceCard = new Card("heart", CardNumber.one);
        Card nineCard = new Card("heart", CardNumber.nine);
        Card kingCard = new Card("heart", CardNumber.king);
    	
    	player.draw(aceCard, Status.PLAYING);
    	dealer.draw(nineCard, Status.PLAYING);	
    	bet.judge(player, dealer);
    	assertEquals(20, bet.refund());

        dealer.draw(kingCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(10, bet.refund());

        player.draw(kingCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(35, bet.refund());

        dealer.draw(aceCard, Status.PLAYING);
        dealer.draw(aceCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(35, bet.refund());
        
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(25, bet.refund());
    }
}
