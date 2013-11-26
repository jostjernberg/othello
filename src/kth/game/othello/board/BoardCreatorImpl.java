package kth.game.othello.board;

import java.util.HashSet;
import java.util.List;

public class BoardCreatorImpl implements BoardCreator {
	public static BoardCreatorImpl INSTANCE = new BoardCreatorImpl();

	private BoardCreatorImpl() {

	}

	/**
	 * Creates a board containing the given nodes.
	 * 
	 * @param nodes
	 *            the nodes of the board
	 * @throws IllegalArgumentException
	 *             if two nodes share the same coordinates
	 * @return the board
	 */
	@Override
	public Board createBoard(List<Node> nodes) {
		if (hasDuplicateNodes(nodes)) {
			throw new IllegalArgumentException("Cannot create board with duplicate nodes.");
		}
		return new BoardImpl(nodes);
	}

	private boolean hasDuplicateNodes(List<Node> nodes) {
		HashSet<String> foundNodes = new HashSet<>();
		for (Node n : nodes) {
			String node = "x=" + n.getXCoordinate() + ",y=" + n.getYCoordinate();
			if (foundNodes.contains(node)) {
				return false;
			} else {
				foundNodes.add(node);
			}
		}
		return true;
	}
}
