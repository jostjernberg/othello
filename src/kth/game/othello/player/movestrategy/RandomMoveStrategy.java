package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * This MoveStrategy will always make random, legal moves.
 */
public class RandomMoveStrategy implements MoveStrategy {
	private String name = "Random move strategy";
	public static RandomMoveStrategy INSTANCE = new RandomMoveStrategy();

	private RandomMoveStrategy() {

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Node move(String playerId, Rules rules, Board board) {
		List<Node> possibleMoves = new ArrayList<>();

		for (Node n : board.getNodes()) {
			if (rules.isMoveValid(playerId, n.getId())) {
				possibleMoves.add(n);
			}
		}

		Collections.shuffle(possibleMoves);

		return possibleMoves.size() == 0 ? null : possibleMoves.get(0);
	}

}
