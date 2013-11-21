package kth.game.othello.score;

import java.util.List;

/**
 * The responsibility of this interface is to supply methods that determine the score for players on a board.
 */
public interface ScoreCountingStrategy {

	public List<ScoreItem> getPlayersScore();

	public int getPoints(String playerId);
}
