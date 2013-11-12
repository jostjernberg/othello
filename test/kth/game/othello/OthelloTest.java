package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OthelloTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// fail("Not yet implemented");
	}

	@Test
	public void replaceNodesTest() {
		List<Node> originalNodes = new ArrayList<>();
		originalNodes.add(new ClassicNode(1, 2));
		originalNodes.add(new ClassicNode(3, 4));
		originalNodes.add(new ClassicNode(5, 6));

		Board originalBoard = new ClassicBoard(originalNodes);
		List<Node> replacementNodes = new ArrayList<>();
		replacementNodes.add(new ClassicNode(3, 4));

		List<Node> newNodes = ClassicOthello.replaceNodes(originalNodes, replacementNodes, "new playerId");

		Assert.assertEquals(originalNodes.get(0).getId(), newNodes.get(0).getId());
		Assert.assertNotEquals(originalNodes.get(1).getId(), newNodes.get(1).getId());
		Assert.assertEquals(originalNodes.get(2).getId(), newNodes.get(2).getId());

		Board newBoard = new ClassicBoard(newNodes);
		Assert.assertEquals(originalBoard.getNodes().get(0).getId(), newBoard.getNodes().get(0).getId());
		Assert.assertNotEquals(originalBoard.getNodes().get(1).getId(), newBoard.getNodes().get(1).getId());
		Assert.assertEquals(originalBoard.getNodes().get(2).getId(), newBoard.getNodes().get(2).getId());
	}
}
