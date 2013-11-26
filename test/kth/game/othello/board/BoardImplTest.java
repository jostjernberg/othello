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

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenAccessingNodeNotPresentOnBoard() {
		BoardImpl board = constructSimpleBoard();
		Assert.assertNotEquals(null, board.getNode(-1, -1));
		Assert.assertNotEquals(null, board.getNode(2, 1));
		Assert.assertNotEquals(null, board.getNode(1, 1));
		Assert.assertNotEquals(null, board.getNode(9, 8));
	}
}
