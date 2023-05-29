package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class CardNumberTest {
	static CardNumber no = CardNumber.one;

	@Test
	public void test() {
		assertEquals(no, CardNumber.one);
	}
	
	@Test
	public void testGetNo() {
		assertEquals("1", no.getNo());
	}
	
	@Test
	public void testGetPoint() {
		assertEquals(11, no.getPoint());
	}
}
