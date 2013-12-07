package kth.game.tournament;

import java.util.List;

public class Tournament {
	List<TournamentRound> tournamentRounds;
	ResultPresenter resultPresenter;
	
	public Tournament(List<TournamentRound> tournamentRounds, ResultPresenter resultPresenter) {
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
	
	private void presentResults() {
		resultPresenter.present(tournamentRounds);
	}
}
