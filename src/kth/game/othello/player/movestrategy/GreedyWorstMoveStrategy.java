package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This MoveStrategy will always choose the legal move that results in the
 * lowest amount of nodes swapped for the playing player.
 */
public class GreedyWorstMoveStrategy implements MoveStrategy {
	private String name = "Worst move greedy strategy";
	public static GreedyWorstMoveStrategy INSTANCE = new GreedyWorstMoveStrategy();

	private GreedyWorstMoveStrategy() {
		// empty
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		Node worstNode = null;
		int minSwaps = Integer.MAX_VALUE;

		for (Node n : board.getNodes()) {
			if(!rules.isMoveValid(playerId, n.getId())) {
				continue;
			}
			int swapsOnMove = rules.getNodesToSwap(playerId, n.getId()).size();
			if (swapsOnMove < minSwaps) {
				worstNode = n;
				minSwaps = swapsOnMove;
			}
		}

		return worstNode;
	}
}
