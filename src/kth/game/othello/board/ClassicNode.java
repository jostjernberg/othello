package kth.game.othello.board;

import kth.game.othello.player.Player;

/**
 * Placeholder class for a classical node.
 */
public class ClassicNode extends AbstractNode {

	private final int y;
	private final int x;
	private Player occupant;
	private boolean marked = false;

	/**
	 * Constructor for a classic node with ordinary coordinates.
	 * 
	 * @param y
	 *            The y coordinate of the node.
	 * @param x
	 *            The x coordinate of the node.
	 */
	public ClassicNode(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	/**
	 * Get the current occupant of this node.
	 * 
	 * @return The player id of the occupant, or null if unoccupied.
	 */
	@Override
	public String getOccupantPlayerId() {
		return this.occupant.getId();
	}

	/**
	 * Get the x coordinate of this node.
	 * 
	 * @return The x coordinate of this node.
	 */
	@Override
	public int getXCoordinate() {
		return this.x;
	}

	/**
	 * Get the y coordinate of this node.
	 * 
	 * @return The y coordinate of this node.
	 */
	@Override
	public int getYCoordinate() {
		return this.y;
	}

	/**
	 * Get the marking status of this node.
	 * 
	 * @return true if and only if the node is marked.
	 */
	@Override
	public boolean isMarked() {
		return this.marked;
	}
}
