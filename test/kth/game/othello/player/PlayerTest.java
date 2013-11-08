package kth.game.othello.player;

import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {
	@Test
	public void testAbleToCreateHumanPlayer() {
		Player p = new HumanPlayer("humanName1");
		Assert.assertEquals("humanName1", p.getName());
		Assert.assertEquals(Player.Type.HUMAN, p.getType());
	}

	@Test
	public void testAbleToCreateComputerPlayer() {
		Player p = new ComputerPlayer("computerName1");
		Assert.assertEquals("computerName1", p.getName());
		Assert.assertEquals(Player.Type.COMPUTER, p.getType());
	}

	@Test
	public void testIDUniqueness() {
		Player cp1 = new ComputerPlayer("computerName1");
		Player cp2 = new ComputerPlayer("computerName2");
		Player hp1 = new HumanPlayer("humanName1");
		Player hp2 = new HumanPlayer("humanName2");
		Assert.assertFalse(cp1.getId().equals(cp2.getId()));
		Assert.assertFalse(cp1.getId().equals(hp1.getId()));
		Assert.assertFalse(cp1.getId().equals(hp2.getId()));
		Assert.assertFalse(cp2.getId().equals(hp1.getId()));
		Assert.assertFalse(cp2.getId().equals(hp2.getId()));
		Assert.assertFalse(hp1.getId().equals(hp2.getId()));
	}
}
