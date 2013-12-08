package kth.game.tournament;

import java.util.List;

import kth.game.tournament.result.ResultPresenter;

public class Tournament {
	List<TournamentRound> tournamentRounds;
	ResultPresenter resultPresenter;
	
	Tournament(List<TournamentRound> tournamentRounds, ResultPresenter resultPresenter) {
		this.tournamentRounds = tournamentRounds;
		this.resultPresenter = resultPresenter;
	}
	
	/**
	 * Assumes that a round is blocking, that is: its start()-method won't return until the game has finished.
	 */
	public void start() {
		for(TournamentRound round : tournamentRounds) {
			round.start();
		}
		presentResults();
	}
	
	public List<TournamentRound> getTournamentRounds() {
		return this.tournamentRounds;
	}
	
	private void presentResults() {
		resultPresenter.present(this);
	}
}
