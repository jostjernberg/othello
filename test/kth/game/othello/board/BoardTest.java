package kth.game.othello.board;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BoardTest {

	@Test
	public void testNodeListConsistency() {
		List<Node> nodes = new ArrayList<Node>();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Node n = Mockito.mock(ClassicNode.class);
				Mockito.when(n.getXCoordinate()).thenReturn(c);
				Mockito.when(n.getYCoordinate()).thenReturn(r);
				nodes.add(n);
			}
		}
		Board b = new ClassicBoard(nodes);
		Assert.assertEquals(8 * 8, b.getNodes().size());
		Iterator<Node> nodeIt = b.getNodes().iterator();

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Node n = nodeIt.next();
				Assert.assertEquals(c, n.getXCoordinate());
				Assert.assertEquals(r, n.getYCoordinate());
			}
		}
	}
}
