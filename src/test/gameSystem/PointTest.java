package gameSystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PointTest {
	static Point point;
	static Card aceCard;
	static Card courtCard;
	static Card twoCard;
	
	@BeforeEach
	public void setup() {
		point = new Point();
		aceCard = new Card(Suit.heart, CardNumber.one);
		courtCard = new Card(Suit.spade, CardNumber.king);
		twoCard = new Card(Suit.diamond, CardNumber.two);
	}

	@Test
	public void testPoint() { //変数が正しく初期化されているか確認
		assertEquals(0, point.getScore());
		assertFalse(point.aceCountCheck()); //aceCountが0なので、メソッドはfalseを返す
	}
	
	@Test
	public void testCalc() { //正しく追加されているかどうか
		point.calc(aceCard);
		assertEquals(11, point.getScore());
		
		point.calc(courtCard);
		assertEquals(21, point.getScore());
		
		point.calc(twoCard); 
		assertEquals(13, point.getScore()); //aceを持ち、21点以上なら21を超えない処理をする
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
