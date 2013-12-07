package kth.game.tournament.result;

import java.util.List;
import java.util.Map;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.TournamentRound;

public interface ResultCountingStrategy {
	
	public Map<MoveStrategy, Integer> getNumberOfWinsForEachStrategy(List<TournamentRound> tournamentRounds);

}
