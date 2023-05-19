package sampleProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;

class sampleTest {

	@Test
	public void instantiate() {
		Card card = new Card("heart", "1");
		assertEquals("heart", card.suit);
		assertEquals("1", card.no);
	}

}
