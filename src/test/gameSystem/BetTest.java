package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BetTest {
    static Bet bet;

    @BeforeEach
    public void setup() {
        bet = new Bet(10);
    }

	@Test
    public void testBet() {
        assertEquals(10, bet.getBet());
    }

    @Test
    public void testCalc() {
        bet.calc("win");
        assertEquals(20, bet.refund());

        bet.calc("draw");
        assertEquals(20, bet.refund());

        bet.calc("lose");
        assertEquals(10, bet.refund());
    }

    @Test
    public void testGetBet() {
        assertEquals(10, bet.getBet());
    }

    @Test
    public void testRefund() {
        bet.calc("win");
        assertEquals(20, bet.refund());
    }
}
