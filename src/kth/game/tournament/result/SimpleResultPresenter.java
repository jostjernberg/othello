package kth.game.tournament.result;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.Tournament;

public class SimpleResultPresenter implements ResultPresenter{
	public static SimpleResultPresenter INSTANCE = new SimpleResultPresenter();
	
	private SimpleResultPresenter() {
		
	}
	
	@Override
	public void present(Tournament tournament) {
		StringBuilder sb = new StringBuilder();
		
		List<MoveStrategy> rankedStrategies = SimpleResultRanker.INSTANCE.rankStrategies(tournament);
		sb.append("Ranking of strategies:\n");
		for(int i = 0; i < rankedStrategies.size(); i++) {
			sb.append(i+1).append(": ").append(rankedStrategies.get(i).getName()).append("\n");
		}
		
		System.out.print(sb.toString());
	}
}
