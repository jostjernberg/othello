package kth.game.othello.board;

public class BoardFactory {

	private static final int CLASSIC_ROWS = 8;
	private static final int CLASSIC_COLUMNS = 8;

	/**
	 * Create a classic board with default size.
	 * 
	 * @return A board.
	 */
	public Board createClassicalBoard() {
		return createCustomSizeBoard(CLASSIC_ROWS, CLASSIC_COLUMNS);
	}

	/**
	 * Create a classic board with custom size.
	 * 
	 * @param rows
	 *            The number of rows in the board.
	 * @param columns
	 *            The number of columns in the board.
	 * @return A board of requested size.
	 */
	public Board createCustomSizeBoard(int rows, int columns) {
		return new ClassicBoard(rows, columns);
	}
}
