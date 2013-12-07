package kth.game.tournament;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Tournament implements Observer {
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
			System.out.println("Starting new round.");
			round.start();
		}
		System.out.println("All rounds ended, printing results.");
		presentResults();
	}
	
	private void presentResults() {
		resultPresenter.present(tournamentRounds);
	}
	
	/**
	 * This method will be called when a round is over.
	 * @param o The instance of TournamentRound that has ended.
	 * @param arg A Score for the players of that round.
	 */
	@Override 
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
