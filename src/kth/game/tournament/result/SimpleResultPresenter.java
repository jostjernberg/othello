package kth.game.tournament.result;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.Tournament;

/**
 * The responsibility of this class is to present the ranking of the strategies in a tournament by printing to stdout.
 */
public class SimpleResultPresenter implements ResultPresenter{
	ResultRanker resultRanker;
	
	public SimpleResultPresenter(ResultRanker resultRanker) {
		this.resultRanker = resultRanker;
	}

	/**
	 * Prints the results of a tournament to stdout as calculated by this instance's ResultRanker.
	 */
	@Override
	public void present(Tournament tournament) {
		StringBuilder sb = new StringBuilder();
		
		List<MoveStrategy> rankedStrategies = resultRanker.rankStrategies(tournament);
		sb.append("Ranking of strategies:\n");
		for(int i = 0; i < rankedStrategies.size(); i++) {
			sb.append(i+1).append(": ").append(rankedStrategies.get(i).getName()).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
