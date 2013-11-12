package kth.game.othello.board;

import java.util.List;

/**
 * A simple classical board with configurable but immutable size.
 */
public class ClassicBoard implements Board {
	private List<Node> nodes;

	public ClassicBoard(List<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * Accessor for the nodes of this board.
	 */
	@Override
	public List<Node> getNodes() {
		return this.nodes;
	}
}
