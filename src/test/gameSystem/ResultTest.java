package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultTest {
	static Result result = Result.WIN;
	
	@Test
	public void testResult() {
		assertEquals(result, Result.WIN);
	}

	@Test
	public void testToBet() {
		assertEquals(2, result.toBet());
	}
	
	@Test
	public void testGetMsg() {
		assertEquals("You Win!!", result.getMsg());
	}
}
