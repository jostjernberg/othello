package kth.game.tournament.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;
import kth.game.tournament.TournamentRound;

public class SimpleResultPresenter implements ResultPresenter{
	public static SimpleResultPresenter INSTANCE = new SimpleResultPresenter();
	
	private SimpleResultPresenter() {
		
	}
	
	private MoveStrategy getWinningStrategy(TournamentRound tournamentRound) {
		MoveStrategy winningStrategy = null;
		
		int maxScore = 0;
		for(Player player : tournamentRound.getPlayers()) {
			int score = tournamentRound.getScore().getPoints(player.getId());
			if(score > maxScore) {
				maxScore = score;
				winningStrategy = player.getMoveStrategy();
			}
		}
		
		return winningStrategy;
	}
	
	private Map<MoveStrategy, Integer> getNumberOfWinsForEachStrategy(List<TournamentRound> tournamentRounds) {
		Map<MoveStrategy, Integer> scores = new HashMap<>();
		for(TournamentRound round : tournamentRounds) {
			MoveStrategy winningStrategy = getWinningStrategy(round); 
			if(!scores.containsKey(winningStrategy)) {
				scores.put(winningStrategy, 0);
			}
			scores.put(winningStrategy, scores.get(winningStrategy) + 1);
		}
		return scores;
	}
	
	@Override
	public void present(List<TournamentRound> tournamentRounds) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Number of wins for each strategy:\n");

		for(Entry<MoveStrategy, Integer> e : getNumberOfWinsForEachStrategy(tournamentRounds).entrySet()) {
			sb.append(e.getKey().getName()).append(": ").append(e.getValue()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
