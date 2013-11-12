package kth.game.othello.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

import org.junit.Test;
import org.mockito.Mockito;

public class NodeTest {

	@Test
	public void testUniqueId() {
		Node a = new ClassicNode(0, 0);
		Node b = new ClassicNode(0, 1);
		Node c = new ClassicNode(0, 2);
		assertNotEquals(a.getId(), b.getId());
		assertNotEquals(a.getId(), c.getId());
		assertNotEquals(b.getId(), c.getId());
	}

	@Test
	public void testFieldInitializationAndAccess() {
		Player p1 = Mockito.mock(HumanPlayer.class);
		Player p2 = Mockito.mock(ComputerPlayer.class);

		Mockito.when(p1.getId()).thenReturn("player1 name");
		Mockito.when(p2.getId()).thenReturn("player2 name");

		Node n1 = new ClassicNode(2, 3, p1);
		Node n2 = new ClassicNode(4, 4, p2);
		Node n3 = new ClassicNode(5, 6);

		assertEquals(2, n1.getXCoordinate());
		assertEquals(3, n1.getYCoordinate());
		assertTrue(n1.isMarked());
		assertEquals("player1 name", n1.getOccupantPlayerId());

		assertEquals(4, n2.getXCoordinate());
		assertEquals(4, n2.getYCoordinate());
		assertTrue(n2.isMarked());
		assertEquals("player2 name", n2.getOccupantPlayerId());

		assertEquals(5, n3.getXCoordinate());
		assertEquals(6, n3.getYCoordinate());
		assertFalse(n3.isMarked());
		assertTrue(null == n3.getOccupantPlayerId());

		assertNotEquals(n1.getId(), n2.getId());
	}
}
