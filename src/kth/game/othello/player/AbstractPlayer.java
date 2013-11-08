package kth.game.othello.player;

/**
 * An abstract representation of an actual player, holding a name, unique player ID as well as the type of the player.
 */
public abstract class AbstractPlayer implements Player {
	private static int nextUniqueId = 0;
	private final String id;
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
	public AbstractPlayer(String name, Type type) {
		this.id = getUniquePlayerId();
		this.name = name;
		this.type = type;
	}

	@Override
	public String getId() {
		return this.id;
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
	private String getUniquePlayerId() {
		String playerId = "Player:" + Integer.toHexString(nextUniqueId);
		nextUniqueId++;
		return playerId;
	}

	/**
	 * Returns a String representation of this AbstractPlayer.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("name:").append(name).append(", ");
		sb.append("id:").append(id).append(", ");
		sb.append("type:").append(type);
		sb.append("}");
		return sb.toString();
	}
}