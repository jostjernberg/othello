package kth.game.othello.board;

import java.util.HashSet;
import java.util.List;

/**
 * The responsibility of this entity is to implement the BoardCreator interface.
 */
public class BoardCreatorImpl implements BoardCreator {
	public static BoardCreatorImpl INSTANCE = new BoardCreatorImpl();

	private BoardCreatorImpl() {
		// empty
	}

	/**
	 * Creates a board containing the given nodes.
	 * 
	 * @throws IllegalArgumentException if two nodes share the same coordinates.
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
			String node = "x" + n.getXCoordinate() + "y" + n.getYCoordinate();
			if (!foundNodes.add(node)) {
				// HashSet.add returns false on duplicate (equal) objects.
				return true;
			}
		}
		return false;
	}
}
