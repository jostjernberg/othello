package kth.game.othello.board;

import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class ClassicNodeTest {

	ClassicNode a, b, c;

	@Before
	public void setUp() throws Exception {
		a = new ClassicNode(0, 0);
		b = new ClassicNode(0, 1);
		c = new ClassicNode(0, 2);
	}

	@Test
	public void testUniqueId() {
		assertNotEquals(a.getId(), b.getId());
		assertNotEquals(a.getId(), c.getId());
		assertNotEquals(b.getId(), c.getId());
	}
}
