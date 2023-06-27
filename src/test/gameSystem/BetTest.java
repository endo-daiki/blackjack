package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BetTest {
    static Bet bet;
    static Card aceCard = new Card(Suit.heart, CardNumber.one);
    static Card nineCard = new Card(Suit.heart, CardNumber.nine);
    static Card kingCard = new Card(Suit.heart, CardNumber.king);

    @BeforeEach
    public void setup() {
        bet = new Bet(10);
    }

	@Test
    public void testBet() { //初期値が正しいか確認
        assertEquals(10, bet.getTip());    
        assertEquals(0, bet.refund());  
    }
	
	@Test
	public void testErrorBet() {
		try {
				bet = new Bet(10);
				bet = new Bet(11);
		    } catch (IllegalArgumentException expected) {
		      assertEquals(expected.getMessage(), "不正な数値です");
		    }
		
		try {
			bet = new Bet(1);
			bet = new Bet(0);
	    } catch (IllegalArgumentException expected) {
	      assertEquals(expected.getMessage(), "不正な数値です");
	    }
	}
	
    @Test
    public void testJudgePlayerBurst() { //プレイヤーがバーストして負けてしまう
    	Player player = new Player();
    	Player dealer = new Player();
        
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        bet.judge(player, dealer); 
        assertEquals(-10, bet.refund());
    }
    
    @Test
    public void testJudgePlayerNaturalBackjack() { //ナチュラルブラックジャックで勝ち
    	Player player = new Player();
    	Player dealer = new Player();
        
        player.draw(kingCard, Status.PLAYING);
        dealer.draw(nineCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(20, bet.refund());
        
        player.draw(aceCard, Status.PLAYING);
        dealer.draw(nineCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(45, bet.refund());
    }
    
    @Test
    public void testJudgePlayerWin() { //プレイヤーの勝ち
    	Player player = new Player();
    	Player dealer = new Player();
        
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        dealer.draw(kingCard, Status.PLAYING);
        dealer.draw(nineCard, Status.PLAYING);
        bet.judge(player, dealer); //点数計算で勝ち
        assertEquals(20, bet.refund());
        
        dealer.draw(nineCard, Status.PLAYING);
        bet.judge(player, dealer); //ディーラーの点数がプレイヤーより多いが、バーストしているので勝ち
        assertEquals(40, bet.refund());
    }
    
    @Test
    public void testJudgePlayerLose() { //プレイヤーの負け
    	Player player = new Player();
    	Player dealer = new Player();
        
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        dealer.draw(kingCard, Status.PLAYING);
        dealer.draw(aceCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(-10, bet.refund());
    }
    
    @Test
    public void testJudgeDraw() { //引き分け
    	Player player = new Player();
    	Player dealer = new Player();
        
        player.draw(kingCard, Status.PLAYING);
        player.draw(kingCard, Status.PLAYING);
        dealer.draw(kingCard, Status.PLAYING);
        dealer.draw(kingCard, Status.PLAYING);
        bet.judge(player, dealer);
        assertEquals(0, bet.refund());
    }
}
