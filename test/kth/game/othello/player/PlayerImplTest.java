package kth.game.othello.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerImplTest {

	@Test
	public void uniqueIdTest() {
		Player p1 = new PlayerImpl("player1name", Player.Type.COMPUTER, null);
		Player p2 = new PlayerImpl("player2name", Player.Type.COMPUTER, null);
		Assert.assertNotEquals(p1.getId(), p2.getId());
	}
}
