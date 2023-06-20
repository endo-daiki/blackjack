package gameSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class PlayerTest {
	static Player player;
	static Card aceCard = new Card(Suit.heart, CardNumber.one);
	static Card kingCard = new Card(Suit.heart, CardNumber.king);
	
	@BeforeEach
	public void setup() {
		player = new Player();
	}

	@Test
	public void testPlayer() {
		assertNotNull(player.getHand());
	}

	@Test
	public void testGetPoint() {
		assertNotNull(player.getPoint(Status.PLAYING));
	}

	@Test
	public void testSplitCheck() {
		assertEquals(false, player.splitCheck());

		player.draw(aceCard, Status.PLAYING);
		player.draw(aceCard, Status.PLAYING);

		assertEquals(true, player.splitCheck());
	}

	@Test
	public void testDraw() {
		player.draw(aceCard, Status.PLAYING);
		assertEquals(11, player.getPoint(Status.PLAYING).getScore());
	}

	@Test
	public void testMovedCheckAll() {
		assertEquals(false, player.movedCheckAll());

		player.draw(kingCard, Status.PLAYING);
		player.draw(kingCard,  Status.PLAYING);
		
		player.split();

		assertEquals(false, player.movedCheckAll());

		player.draw(kingCard, Status.PLAYING);
		player.draw(kingCard, Status.PLAYING);
		player.isStand(Status.SPLIT);
		
		assertEquals(true, player.movedCheckAll());
	}

	@Test
	public void testBurstCheckAll() {
		player.draw(kingCard, Status.PLAYING);
		assertEquals(false, player.burstCheckAll());

		player.draw(kingCard, Status.PLAYING);
		player.split();
		player.draw(kingCard, Status.PLAYING);
		player.draw(kingCard, Status.PLAYING);

		assertEquals(false, player.burstCheckAll());

		player.draw(kingCard, Status.SPLIT);
		player.draw(kingCard, Status.SPLIT);

		assertEquals(true, player.burstCheckAll());
	}

	@Test
	public void testIsStand() {
		player.isStand(Status.PLAYING);
		assertEquals(true, player.movedCheckAll());
	}

	@Test
	public void testSplit() {
		player.draw(aceCard, Status.PLAYING);
		player.draw(aceCard, Status.PLAYING);
		player.split();

		assertEquals(2, player.getHand().size());
	}
}
