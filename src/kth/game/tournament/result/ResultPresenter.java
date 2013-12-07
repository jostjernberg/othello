package kth.game.tournament.result;

import java.util.List;

import kth.game.tournament.TournamentRound;

public interface ResultPresenter {

	public void present(List<TournamentRound> tournamentRounds);

}
