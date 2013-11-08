package kth.game.othello.player;

/**
 * An abstract representation of an actual player.
 */
abstract class PlayerInstance implements Player {
	private static int nextUniqueID = 0;
	private final String ID;
	private final String name;
	private final Type type;

	/**
	 * Create an immutable player with a unique ID in the context of all players.
	 * 
	 * @param name
	 *            Name of the player.
	 * @param type
	 *            Type of the player.
	 */
	public PlayerInstance(String name, Type type) {
		this.ID = getUniquePlayerID();
		this.name = name;
		this.type = type;
	}

	@Override
	public String getId() {
		return this.ID;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Type getType() {
		return this.type;
	}

	/**
	 * Creates a new player ID as a String representation of a hex value.
	 * 
	 * @return A unique ID of the form "Player:[hex-value]" in the context of all players.
	 */
	private String getUniquePlayerID() {
		String playerID = "Player:" + Integer.toHexString(nextUniqueID);
		nextUniqueID++;
		return playerID;
	}
}