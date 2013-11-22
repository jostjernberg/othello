package kth.game.othello.score;

import java.util.List;

import kth.game.othello.board.Board;

/**
 * The responsibility of this interface is to supply methods that determine the score for players on a board.
 */
public interface ScoreCountingStrategy {

	/**
	 * Calculate the score of a set of players on a board.
	 * 
	 * @param board
	 *            The board on which the score will be calculated.
	 * @return A list of the scores.
	 */
	public List<ScoreItem> getPlayersScore(Board board);

	/**
	 * Calculate the score of the player with playerId on the board.
	 * 
	 * @param board
	 *            The board on which the score will be calculated.
	 * @param playerId
	 *            The id of the player of which the score will be calculated.
	 * @return The score of the player.
	 * @throws IllegalArgumentException
	 *             if these is no player present on the board with id playerId.
	 */
	public int getPoints(Board board, String playerId) throws IllegalArgumentException;
}
