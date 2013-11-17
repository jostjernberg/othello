package kth.game.othello;

/**
 * The current game state.
 */
public enum OthelloState {
	/** Game has not yet started. */
	STARTUP,

	/** Game is ongoing. */
	ACTIVE,

	/** Game has ended. */
	FINISHED;
}