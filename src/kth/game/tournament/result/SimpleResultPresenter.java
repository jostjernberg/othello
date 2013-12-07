package kth.game.tournament.result;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.TournamentRound;

public class SimpleResultPresenter implements ResultPresenter{
	public static SimpleResultPresenter INSTANCE = new SimpleResultPresenter();
	
	private SimpleResultPresenter() {
		
	}
	
	@Override
	public void present(List<TournamentRound> tournamentRounds) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number of wins for each strategy:\n");

		Map<MoveStrategy, Integer> wins = SimpleResultCountingStrategy.INSTANCE.getNumberOfWinsForEachStrategy(tournamentRounds);
		
		for(Entry<MoveStrategy, Integer> e : wins.entrySet()) {
			sb.append(e.getKey().getName()).append(": ").append(e.getValue()).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
