package kth.game.othello.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * The responsibility of this entity is to calculate the score for players present on a board, where each occupied
 * square is worth one point for the occupying player.
 */
public class ClassicScoreCountingStrategy implements ScoreCountingStrategy {
	public static ClassicScoreCountingStrategy INSTANCE = new ClassicScoreCountingStrategy();

	private ClassicScoreCountingStrategy() {
		// empty
	}

	@Override
	public List<ScoreItem> getPlayersScore(Board board) {
		Map<String, Integer> score = new HashMap<>();

		for (Node n : board.getNodes()) {
			if (!n.isMarked()) {
				continue;
			}
			if (!score.containsKey(n.getOccupantPlayerId())) {
				score.put(n.getOccupantPlayerId(), new Integer(0));
			}
			score.put(n.getOccupantPlayerId(), score.get(n.getOccupantPlayerId()) + 1);
		}

		List<ScoreItem> scoreItems = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : score.entrySet()) {
			scoreItems.add(new ScoreItem(entry.getKey(), entry.getValue()));
		}

		// sort scores in descending order
		Collections.sort(scoreItems);
		Collections.reverse(scoreItems);

		return scoreItems;
	}

	@Override
	public int getPoints(Board board, String playerId) {
		List<ScoreItem> scoreItems = getPlayersScore(board);

		for (ScoreItem si : scoreItems) {
			if (si.getPlayerId().equals(playerId)) {
				return si.getScore();
			}
		}

		throw new IllegalArgumentException();
	}
}
