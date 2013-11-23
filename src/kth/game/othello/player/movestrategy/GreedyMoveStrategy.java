package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This MoveStrategy will always choose the legal move that results in the largest amount of nodes swapped for the
 * playing player.
 */
public class GreedyMoveStrategy implements MoveStrategy {
	private String name = "Greedy move strategy";
	private static GreedyMoveStrategy INSTANCE = new GreedyMoveStrategy();

	private GreedyMoveStrategy() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		Node bestNode = null;
		int maxSwaps = 0;

		for (Node n : board.getNodes()) {
			int swapsOnMove = rules.getNodesToSwap(playerId, n.getId()).size();
			if (swapsOnMove > maxSwaps) {
				bestNode = n;
				maxSwaps = swapsOnMove;
			}
		}

		return bestNode;
	}
}
