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
    }

    @Test
    public void testCalc() { //結果ごとに返金の合計が変化
        bet.calc("win"); //勝ったら2倍になる
        assertEquals(20, bet.refund());

        bet.calc("draw"); //引き分けは変動なし
        assertEquals(20, bet.refund());

        bet.calc("lose"); //負けたら掛金額を没収
        assertEquals(10, bet.refund());
        
        bet.calc("naturalwin"); //ナチュラルBJは2.5倍を追加
        assertEquals(35, bet.refund());
    }

    @Test
    public void testGetBet() { //最初の掛金額を取得
        assertEquals(10, bet.getTip());
    }

    @Test
    public void testRefund() { //返金額を取得
        bet.calc("win"); 
        assertEquals(20, bet.refund()); //掛金の2倍の額を取得できる
    }
}
