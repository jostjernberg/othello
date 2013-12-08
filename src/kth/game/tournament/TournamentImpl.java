package kth.game.tournament;

import java.util.List;

import kth.game.tournament.result.ResultPresenter;

/**
 * The responsibility of this class is to execute a set of tournament rounds and present the result when they have all finished.
 */
class TournamentImpl implements Tournament {
	private List<TournamentRound> tournamentRounds;
	private ResultPresenter resultPresenter;

	TournamentImpl(List<TournamentRound> tournamentRounds, ResultPresenter resultPresenter) {
		this.tournamentRounds = tournamentRounds;
		this.resultPresenter = resultPresenter;
	}

	/**
	 * Starts this tournament. Assumes that a round is blocking, that is: its start()-method won't return until the game has finished.
	 */
	public void start() {
		for (TournamentRound round : tournamentRounds) {
			round.start();
		}
		presentResults();
	}

	/**
	 * Returns the rounds of this tournament, sorted in the order that they are played.
	 */
	public List<TournamentRound> getTournamentRounds() {
		return this.tournamentRounds;
	}
	
	private void presentResults() {
		resultPresenter.present(this);
	}
}
