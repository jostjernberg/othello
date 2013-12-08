package kth.game.tournament.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.ScoreItem;
import kth.game.tournament.Tournament;
import kth.game.tournament.TournamentRound;

/**
 * The responsibility of this class is to rank all strategies in a tournament. The winning strategy in each matchup is given 
 * 2 points and the loser 0 points. Both strategies gets one point in the case of a tie. All strategies are then ranked,
 * based on their scores.
 */
public class SimpleResultRanker implements ResultRanker {
	public static SimpleResultRanker INSTANCE = new SimpleResultRanker();
	
	private SimpleResultRanker() {
		// empty
	}
	
	// Creates a map where all strategies in a tournament are present as keys with values of 0.
	private Map<MoveStrategy, Integer> initMapWithAllStrategies(Tournament tournament) {
		Map<MoveStrategy, Integer> map = new HashMap<>();
		for(TournamentRound round : tournament.getTournamentRounds()) {
			for(Player p : round.getPlayers()) {
				map.put(p.getMoveStrategy(), 0);
			}
		}
		return map;
	}
	
	// Maps the score of each strategy in the tournament.
	private Map<MoveStrategy, Integer> getScoreForEachStrategy(Tournament tournament) {
		Map<MoveStrategy, Integer> scores = initMapWithAllStrategies(tournament);
		
		for(TournamentRound round : tournament.getTournamentRounds()) {
			List<ScoreItem> score = round.getScore().getPlayersScore();
			if(score.size() == 1) {
				MoveStrategy strategy = round.getPlayerWithId(score.get(0).getPlayerId()).getMoveStrategy();
				scores.put(strategy, scores.get(strategy) + 2);
			} else if(score.get(0).getScore() > score.get(1).getScore()) {
				MoveStrategy strategy = round.getPlayerWithId(score.get(0).getPlayerId()).getMoveStrategy();
				scores.put(strategy, scores.get(strategy) + 2);
			} else if(score.get(0).getScore() < score.get(1).getScore()) {
				MoveStrategy strategy = round.getPlayerWithId(score.get(1).getPlayerId()).getMoveStrategy();
				scores.put(strategy, scores.get(strategy) + 2);
			} else {
				MoveStrategy strategyOne = round.getPlayerWithId(score.get(0).getPlayerId()).getMoveStrategy();
				MoveStrategy strategyTwo = round.getPlayerWithId(score.get(1).getPlayerId()).getMoveStrategy();
				scores.put(strategyOne, scores.get(strategyOne) + 1);
				scores.put(strategyTwo, scores.get(strategyTwo) + 1);
			}		
		}
		return scores;
	}
	
	@Override
	public List<MoveStrategy> rankStrategies(Tournament tournament) {
		List<MoveStrategy> rankedStrategies = new ArrayList<>();
		Map<MoveStrategy, Integer> strategyScores = getScoreForEachStrategy(tournament);
		
		int numScores = strategyScores.size();
		for(int i = 0; i < numScores; i++) {
			MoveStrategy bestStrategy = null;
			int maxScore = -1;
			for(Entry<MoveStrategy, Integer> entry : strategyScores.entrySet()) {
				if(entry.getValue() > maxScore) {
					bestStrategy = entry.getKey();
					maxScore = entry.getValue();
				}
			}
			rankedStrategies.add(bestStrategy);
			strategyScores.remove(bestStrategy);
		}
		
		return rankedStrategies;
	}

}
