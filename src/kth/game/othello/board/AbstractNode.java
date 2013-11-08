package kth.game.othello.board;

/**
 * An abstract node implementation which makes sure that all nodes have a unique
 * id.
 */
public abstract class AbstractNode implements Node {

	private static int nextUniqueId = 0;
	private final String id;

	/**
	 * Constructor which generates a unique id for the node.
	 */
	public AbstractNode() {
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
}
