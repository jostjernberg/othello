package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple classical board with configurable but immutable size.
 */
public class ClassicBoard implements InternalBoard {
	private List<Node> nodes;

	public static final int DEFAULT_ROWS = 8;
	public static final int DEFAULT_COLUMNS = 8;

	private final int rows;
	private final int columns;

	/**
	 * Constructor which creates a classical board of default size containing
	 * classical nodes.
	 */
	public ClassicBoard() {
		this(DEFAULT_ROWS, DEFAULT_COLUMNS);
	}

	/**
	 * Constructor which creates a classical board of specified size containing
	 * classical nodes.
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
				Node n = new ClassicNode(x, y);
				this.nodes.add(n);
			}
		}
	}

	/**
	 * Accessor for the nodes of this board.
	 */
	@Override
	public List<Node> getNodes() {
		return this.nodes;
	}

	@Override
	public int getColumns() {
		return this.columns;
	}

	@Override
	public int getRows() {
		return this.rows;
	}

	/**
	 * Get the node located on the specified coordinates of the board.
	 */
	@Override
	public Node getNode(int y, int x) {
		return this.nodes.get(coordinateToPosition(y, x));
	}

	/**
	 * Change the occupant on the specified board position.
	 */
	@Override
	public void changeOccupant(int y, int x, String newPlayerId) {
		int position = coordinateToPosition(y, x);
		Node node = new ClassicNode(x, y, newPlayerId);
		this.nodes.set(position, node);
	}

	private int coordinateToPosition(int y, int x) {
		if (y >= rows || x >= columns || y < 0 || x < 0) {
			throw new IndexOutOfBoundsException("Invalid coordinate for this board.");
		}
		return columns * y + x;
	}
}
