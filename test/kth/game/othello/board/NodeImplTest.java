package kth.game.othello.board;

import org.junit.Assert;
import org.junit.Test;

public class NodeImplTest {

	@Test
	public void observerTest() {
		// TODO
	}

	@Test
	public void uniqueIdTest() {
		Node n1 = new NodeImpl(0, 0);
		Node n2 = new NodeImpl(0, 0);
		Assert.assertNotEquals(n1.getId(), n2.getId());
	}
}
