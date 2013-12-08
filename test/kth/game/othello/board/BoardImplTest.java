package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.TestUtil;

import org.junit.Assert;
import org.junit.Test;

public class BoardImplTest {
	TestUtil testUtil = new TestUtil();

	BoardImpl board;

	private BoardImpl constructSimpleBoard() {
		List<Node> nodes = new ArrayList<>();
		nodes.add(testUtil.mockNode(1, 2, "playerId1", null));
		nodes.add(testUtil.mockNode(2, 2, null, null));
		nodes.add(testUtil.mockNode(0, 0, null, null));
		nodes.add(testUtil.mockNode(8, 9, "playerId2", null));

		return new BoardImpl(nodes);
	}

	@Test
	public void correctGetMaxX() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertEquals(8, board.getMaxX());
	}

	@Test
	public void correctGetMaxY() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertEquals(9, board.getMaxY());
	}

	@Test
	public void hasNode() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertTrue(board.hasNode(1, 2));
		Assert.assertTrue(board.hasNode(2, 2));
		Assert.assertTrue(board.hasNode(0, 0));
		Assert.assertTrue(board.hasNode(8, 9));
		Assert.assertFalse(board.hasNode(8, 8));
		Assert.assertFalse(board.hasNode(2, 4));
		Assert.assertFalse(board.hasNode(1, 1));
		Assert.assertFalse(board.hasNode(0, 1));
		Assert.assertFalse(board.hasNode(1, 0));
	}

	@Test
	public void canGetPlayerIdToPrintableTagTest() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertNotEquals(null, board.playerIdToPrintableTag("playerId1"));
		Assert.assertNotEquals(null, board.playerIdToPrintableTag("playerId2"));
		Assert.assertEquals(null, board.playerIdToPrintableTag("playerId3"));
	}

	@Test
	public void canAccessNodes() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertNotEquals(null, board.getNode(1, 2));
		Assert.assertNotEquals(null, board.getNode(2, 2));
		Assert.assertNotEquals(null, board.getNode(0, 0));
		Assert.assertNotEquals(null, board.getNode(8, 9));
	}

	@Test
	public void nodesAreOrdered() {
		List<Node> nodes = new ArrayList<>();

		// 3x3 board with nodes added in arbitrary order
		nodes.add(testUtil.mockNode(0, 0, "playerId1", null));
		nodes.add(testUtil.mockNode(2, 0, null, null));
		nodes.add(testUtil.mockNode(1, 2, null, null));
		nodes.add(testUtil.mockNode(2, 1, null, null));
		nodes.add(testUtil.mockNode(0, 2, null, null));
		nodes.add(testUtil.mockNode(1, 0, "playerId2", null));
		nodes.add(testUtil.mockNode(1, 1, null, null));
		nodes.add(testUtil.mockNode(0, 1, null, null));
		nodes.add(testUtil.mockNode(2, 2, null, null));
		Board b = new BoardImpl(nodes);

		// Verify that getNodes() returns all nodes in order
		// (y ascending, then x ascending)
		int lastY = -1;
		int lastX = -1;
		for (Node n : b.getNodes()) {
			int x = n.getXCoordinate();
			int y = n.getYCoordinate();
			Assert.assertTrue(y >= lastY);
			Assert.assertTrue(x > lastX || lastY != y);
			lastX = x;
			lastY = y;
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenAccessingNodeNotPresentOnBoard() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertNotEquals(null, board.getNode(-1, -1));
		Assert.assertNotEquals(null, board.getNode(2, 1));
		Assert.assertNotEquals(null, board.getNode(1, 1));
		Assert.assertNotEquals(null, board.getNode(9, 8));
	}
}
