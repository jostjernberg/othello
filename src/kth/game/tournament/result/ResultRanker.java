package kth.game.tournament.result;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.Tournament;

/**
 * The responsibility of this interface is to rank the strategies in a tournament.
 */
public interface ResultRanker {
	
	/**
	 * Rank the strategies in a tournament.
	 * @param tournament
	 * @return A sorted list of strategies, where the first element was the best strategy, sorted descendingly.
	 */
	public List<MoveStrategy> rankStrategies(Tournament tournament);

}
