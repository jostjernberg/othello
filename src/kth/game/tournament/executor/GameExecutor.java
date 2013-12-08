package kth.game.tournament.executor;

/**
 * The responsibility of classes implementing this interface is to ensure that a game is played from start to end.
 */
public interface GameExecutor {
	
	/**
	 * Start the execution of this game.
	 * @param playerId The player that will make the first move.
	 */
	public void start(String playerId);
	
}
