package kth.game.othello.board;

public interface InternalBoard extends Board {

	/**
	 * Get the number of columns in the board.
	 */
	public int getColumns();

	/**
	 * Get the number of rows in the board.
	 */
	public int getRows();

	/**
	 * Print a user-friendly version of the board.
	 */
	public String toString();

	/**
	 * Get the node located on the specified coordinates of the board.
	 * 
	 * @return The node
	 */
	public Node getNode(int x, int y);

	/**
	 * Change the occupant on the specified board position.
	 */
	public void changeOccupant(int x, int y, String newPlayerId);

}
