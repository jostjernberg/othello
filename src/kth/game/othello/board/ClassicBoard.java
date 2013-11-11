package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple classical board with configurable but immutable size.
 */
public class ClassicBoard implements Board {

	public static final int DEFAULT_ROWS = 8;
	public static final int DEFAULT_COLUMNS = 8;

	private final int rows;
	private final int columns;

	/**
	 * Returns the number of rows in this Board.
	 * 
	 * @return Number of rows.
	 */
	public int numRows() {
		return rows;
	}

	/**
	 * Returns the number of columns in this Board.
	 * 
	 * @return Number of columns.
	 */
	public int numColumns() {
		return columns;
	}

	private List<Node> nodes;

	/**
	 * Constructor which creates a classical board of default size containing classical nodes.
	 */
	public ClassicBoard() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}

	/**
	 * Constructor which creates a classical board of specified size containing classical nodes.
	 * 
	 * @param rows
	 *            Number of rows in the board.
	 * @param columns
	 *            Number of columns in the board.
	 */
	public ClassicBoard(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		createNodes();
	}

	private void createNodes() {
		if (this.nodes != null) {
			throw new RuntimeException("Nodes already created for current board.");
		}
		this.nodes = new ArrayList<Node>();
		for (int y = 0; y < this.rows; y++) {
			for (int x = 0; x < this.columns; x++) {
				Node n = new ClassicNode(y, x);
				this.nodes.add(n);
			}
		}
	}

	/**
	 * Get the node located on the specified coordinates of the board.
	 * 
	 * @param y
	 *            The y coordinate of the node.
	 * @param x
	 *            The x coordinate of the node.
	 * @return The node
	 */
	public Node getNode(int y, int x) {
		if (y >= rows || x >= columns || y < 0 || x < 0) {
			throw new IllegalArgumentException("Invalid coordinate for this board.");
		}
		final int position = columns * y + x;
		return this.nodes.get(position);
	}

	/**
	 * Accessor for the nodes of this board.
	 */
	@Override
	public List<Node> getNodes() {
		return this.nodes;
	}
}
