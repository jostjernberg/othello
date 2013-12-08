package kth.game.othello.player;

import java.util.UUID;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * The responsibility of this entity is to implement the Player interface.
 */
public class PlayerImpl implements Player {
	private UUID id = UUID.randomUUID();
	private MoveStrategy moveStrategy;
	private String name;
	private Type type;

	/**
	 * Creates a new PlayerImpl.
	 * 
	 * @param name
	 *            Name of the player.
	 * @param type
	 *            Type of the player.
	 * @param moveStrategy
	 *            This must be set if type == COMPUTER. If type == HUMAN this value will never be used and can thus be
	 *            null.
	 */
	PlayerImpl(String name, Type type, MoveStrategy moveStrategy) {
		this.name = name;
		this.type = type;
		this.moveStrategy = moveStrategy;
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public MoveStrategy getMoveStrategy() {
		return moveStrategy;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setMoveStrategy(MoveStrategy moveStrategy) {
		this.moveStrategy = moveStrategy;
	}

}