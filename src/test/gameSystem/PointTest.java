package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;

class PointTest {
	static Point point;
	static Card aceCard;
	static Card courtCard;
	static Card towCard;
	
	@BeforeEach
	public void setup() {
		point = new Point();
		aceCard = new Card("heart", "1");
		courtCard = new Card("spade", "k");
		towCard = new Card("diamond", "2");
	}
	
	@Test
	public void testCalc() {
		point.calc(aceCard);
		assertEquals(11, point.point);
		point.calc(courtCard);
		assertEquals(21, point.point);
		point.calc(towCard);
		assertEquals(13, point.point);
	}
	
	@Test
	public void testBurstCheck() {
		assertEquals(false, point.burstCheck());
	}
	
	@Test
	public void testBjCheck() {
		assertEquals(false, point.bjCheck());
	}
	
	@Test
	public void testGetPoint() {
		point.calc(aceCard);
		assertEquals(11, point.getPoint());
	}
	
	@Test
	public void testAceCount() {
		assertEquals(false, point.aceCount());
	}

}
