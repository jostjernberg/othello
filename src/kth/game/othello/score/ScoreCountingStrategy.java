package kth.game.othello.score;

import kth.game.othello.board.Board;

/**
 * The responsibility of this interface is to supply methods that determine the score for players on a board.
 */
public interface ScoreCountingStrategy {

	/**
	 * Calculate the score for the player with playerId on the given board.
	 * 
	 * @param board
	 * @param players
	 * @return
	 */
	public int getPlayerScore(Board board, String playerId);
}
