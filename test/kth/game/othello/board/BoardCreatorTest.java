package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class BoardCreatorTest {

	public Node mockNode(int x, int y, String occupantId) {
		Node n = Mockito.mock(Node.class);
		Mockito.when(n.getOccupantPlayerId()).thenReturn(occupantId);
		Mockito.when(n.getXCoordinate()).thenReturn(x);
		Mockito.when(n.getYCoordinate()).thenReturn(y);
		return n;
	}

	@Test
	public void testCreateSmallBoard() {
		BoardCreator bc = BoardCreatorImpl.INSTANCE;

		List<Node> nodes = new ArrayList<>();
		nodes.add(mockNode(2, 2, null));
		nodes.add(mockNode(2, 3, null));
		nodes.add(mockNode(3, 2, null));
		nodes.add(mockNode(3, 3, "1"));

		bc.createBoard(nodes);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBoardWithDuplicateNodes() {
		BoardCreator bc = BoardCreatorImpl.INSTANCE;

		List<Node> nodes = new ArrayList<>();
		nodes.add(mockNode(3, 2, null));
		nodes.add(mockNode(2, 2, null));
		nodes.add(mockNode(3, 2, "1")); // duplicate

		bc.createBoard(nodes);
	}
}
