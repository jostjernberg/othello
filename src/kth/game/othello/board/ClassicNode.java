package kth.game.othello.board;

/**
 * An abstract node implementation which makes sure that all nodes have a unique id.
 */
public class ClassicNode implements Node {

	private static int nextUniqueId = 0;
	private final int y;
	private final int x;
	protected final String occupantPlayerId;
	private final String id;

	/**
	 * Constructor which generates a unique id for the node without a occupant player.
	 * 
	 * @param x
	 *            The x coordinate of the node.
	 * @param y
	 *            The y coordinate of the node.
	 */
	public ClassicNode(int x, int y) {
		this(x, y, null);
	}

	/**
	 * Constructor which generates a unique id for the node
	 * 
	 * @param x
	 *            The x coordinate of the node.
	 * @param y
	 *            The y coordinate of the node.
	 * @param occupantPlayerId
	 *            The occupant player's id.
	 */
	public ClassicNode(int x, int y, String occupantPlayerId) {
		this.x = x;
		this.y = y;
		this.occupantPlayerId = occupantPlayerId;
		this.id = getUniqueId();
	}

	/**
	 * Get node id.
	 * 
	 * @return The id of this node.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Generates a unique node id as a String representation of a hex value.
	 * 
	 * @return A unique id for each node.
	 */
	private static String getUniqueId() {
		return Integer.toHexString(nextUniqueId++);
	}

	/**
	 * Get the current occupant of this node.
	 * 
	 * @return The player id of the occupant, or null if unoccupied.
	 */
	@Override
	public String getOccupantPlayerId() {
		return this.occupantPlayerId;
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
		return occupantPlayerId != null;
	}
}
