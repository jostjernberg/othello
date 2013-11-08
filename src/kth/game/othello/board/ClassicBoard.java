package kth.game.othello.board;

import java.util.List;

/**
 * A simple classical board with configurable size.
 */
public class ClassicBoard implements Board {

	private final int rows;
	private final int columns;

	/**
	 * Constructor which creates a classical board of specified size.
	 * 
	 * @param rows
	 *            Number of rows in the board.
	 * @param columns
	 *            Number of columns in the board.
	 */
	public ClassicBoard(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	/**
	 * Accessor for the nodes of this board.
	 */
	@Override
	public List<Node> getNodes() {
		return null;
	}
}
