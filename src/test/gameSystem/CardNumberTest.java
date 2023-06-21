package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardNumberTest {
	static CardNumber no = CardNumber.one;

	@Test
	public void testCardNumber() {
		assertEquals(no, CardNumber.one);
	}

	@Test
	public void testGetPoint() {
		assertEquals(11, no.getPoint());
	}
}
