package kth.game.tournament;

import java.util.List;

public interface Tournament {
	
	/**
	 * Starts this tournament and presents the results when all rounds have ended.
	 */
	public void start();

	/**
	 * Returns the rounds of this tournament, sorted in the order that they are played.
	 */
	public List<TournamentRound> getTournamentRounds();
	
}
