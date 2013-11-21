package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to performe player moves on a board.
 */
public class MoveHandler {
	Board board;

	public MoveHandler(Board board) {
		this.board = board;
	}

	/**
	 * If the player in turn is a computer then this computer makes a move and updates the player in turn. All observers
	 * will be notified with the additional argument being the list of nodes that were swapped.
	 * 
	 * @return the nodes that where swapped for this move, including the node where the player made the move.
	 * @throws IllegalStateException
	 *             if there is not a computer in turn.
	 */
	public List<Node> move() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Validates if the move is correct and if the player is in turn. If so, then the move is made which updates the
	 * board and the player in turn. All observers will be notified with the additional argument being the list of nodes
	 * that were swapped.
	 * 
	 * @param playerId
	 *            the id of the player that makes the move.
	 * @param nodeId
	 *            the id of the node where the player wants to move.
	 * @return the nodes that where swapped for this move, including the node where the player made the move.
	 * @throws IllegalArgumentException
	 *             if the move is not valid, or if the player is not in turn.
	 */
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
