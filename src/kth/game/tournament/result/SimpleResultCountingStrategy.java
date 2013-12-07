package kth.game.tournament.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.TournamentRound;

public class SimpleResultCountingStrategy implements ResultCountingStrategy {
	public static SimpleResultCountingStrategy INSTANCE = new SimpleResultCountingStrategy();
	
	private SimpleResultCountingStrategy() {
		// empty
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
	
	@Override
	public Map<MoveStrategy, Integer> getNumberOfWinsForEachStrategy(List<TournamentRound> tournamentRounds) {
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

}
