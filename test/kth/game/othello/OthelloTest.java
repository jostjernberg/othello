package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OthelloTest {

	@Test
	public void replaceNodesTest() {
		List<Node> originalNodes = new ArrayList<>();

		Node originalNode1 = Mockito.mock(ClassicNode.class);
		Node originalNode2 = Mockito.mock(ClassicNode.class);
		Node originalNode3 = Mockito.mock(ClassicNode.class);

		Mockito.when(originalNode1.getXCoordinate()).thenReturn(1);
		Mockito.when(originalNode1.getYCoordinate()).thenReturn(2);
		Mockito.when(originalNode2.getXCoordinate()).thenReturn(3);
		Mockito.when(originalNode2.getYCoordinate()).thenReturn(4);
		Mockito.when(originalNode3.getXCoordinate()).thenReturn(5);
		Mockito.when(originalNode3.getYCoordinate()).thenReturn(6);
		Mockito.when(originalNode1.getId()).thenReturn("a");
		Mockito.when(originalNode2.getId()).thenReturn("b");
		Mockito.when(originalNode3.getId()).thenReturn("c");

		originalNodes.add(originalNode1);
		originalNodes.add(originalNode2);
		originalNodes.add(originalNode3);

		List<Node> replacementNodes = new ArrayList<>();

		Node replacementNode = Mockito.mock(ClassicNode.class);

		Mockito.when(replacementNode.getXCoordinate()).thenReturn(3);
		Mockito.when(replacementNode.getYCoordinate()).thenReturn(4);
		Mockito.when(replacementNode.getId()).thenReturn("d");

		replacementNodes.add(replacementNode);

		List<Node> newNodes = ClassicOthello.replaceNodes(originalNodes, replacementNodes, "new playerId");

		Assert.assertEquals(originalNodes.get(0).getId(), newNodes.get(0).getId());
		Assert.assertNotEquals(originalNodes.get(1).getId(), newNodes.get(1).getId());
		Assert.assertEquals(originalNodes.get(2).getId(), newNodes.get(2).getId());
	}
}
