package kth.game.othello.player;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

/**
 * The responsibility of this entity is to implement the PlayerCreator interface.
 */
public class PlayerCreatorImpl implements PlayerCreator {
	public static PlayerCreatorImpl INSTANCE = new PlayerCreatorImpl();
	private MoveStrategy defaultStrategy = RandomMoveStrategy.INSTANCE;

	private PlayerCreatorImpl() {
		// empty
	}

	@Override
	public Player createComputerPlayer(String name) {
		return new PlayerImpl(name, Player.Type.COMPUTER, defaultStrategy);
	}

	@Override
	public Player createComputerPlayer(String name, MoveStrategy moveStrategy) {
		return new PlayerImpl(name, Player.Type.COMPUTER, moveStrategy);
	}

	@Override
	public Player createHumanPlayer(String name) {
		return new PlayerImpl(name, Player.Type.HUMAN, null);
	}

}
