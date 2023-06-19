package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	static Point point;
	static Card aceCard;
	static Card courtCard;
	static Card towCard;
	
	@BeforeEach
	public void setup() {
		point = new Point();
		aceCard = new Card("heart", CardNumber.one);
		courtCard = new Card("spade", CardNumber.king);
		towCard = new Card("diamond", CardNumber.two);
	}

	@Test
	public void testPoint() {
		assertEquals(0, point.getScore());
	}
	
	@Test
	public void testCalc() {
		point.calc(aceCard);
		assertEquals(11, point.getScore());
		
		point.calc(courtCard);
		assertEquals(21, point.getScore());
		
		point.calc(towCard);
		assertEquals(13, point.getScore());
	}
	
	@Test
	public void testBurstCheck() {
		assertEquals(false, point.burstCheck());
		point.calc(courtCard);
		point.calc(courtCard);
		point.calc(courtCard);
		assertEquals(true, point.burstCheck());
	}
	
	@Test
	public void testBjCheck() {
		assertEquals(false, point.bjCheck());
		point.calc(aceCard);
		point.calc(courtCard);
		assertEquals(true, point.bjCheck());
	}
	
	@Test
	public void testGetScore() {
		point.calc(aceCard);
		assertEquals(11, point.getScore());
	}
	
	@Test
	public void testAceCountCheck() {
		assertEquals(false, point.aceCountCheck());
		point.calc(aceCard);
		assertEquals(true, point.aceCountCheck());
	}

}
