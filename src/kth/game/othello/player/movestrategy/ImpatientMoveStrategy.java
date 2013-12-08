package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This MoveStrategy will always make the first legal move.
 */
public class ImpatientMoveStrategy implements MoveStrategy {
	private String name = "Impatient move strategy";
	public static ImpatientMoveStrategy INSTANCE = new ImpatientMoveStrategy();

	private ImpatientMoveStrategy() {
		// empty
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		for (Node n : board.getNodes()) {
			if (rules.isMoveValid(playerId, n.getId())) {
				return n;
			}
		}
		return null;
	}
}
