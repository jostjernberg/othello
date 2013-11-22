package kth.game.othello.player;

import java.util.UUID;

import kth.game.othello.player.movestrategy.MoveStrategy;

/**
 * An immutable player representation with type Player.Type.COMPUTER.
 */
public class PlayerImpl implements Player {
	private UUID id = UUID.randomUUID();
	private MoveStrategy moveStrategy;
	private String name;
	private Type type;

	PlayerImpl(String name, Type type, MoveStrategy moveStrategy) {
		this(name, type);
		this.moveStrategy = moveStrategy;
	}

	PlayerImpl(String name, Type type) {
		this.name = name;
		this.type = type;
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