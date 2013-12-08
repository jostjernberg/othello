package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to perform player moves on a board according to a set of rules.
 */
class MoveHandler {
	private Board board;
	private Rules rules;
	private TurnHandler turnHandler;

	/**
	 * Creates a new move handler, using a board, a set of rules and a turn handler.
	 */
	public MoveHandler(Board board, Rules rules, TurnHandler turnHandler) {
		this.board = board;
		this.rules = rules;
		this.turnHandler = turnHandler;
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
		Player player = turnHandler.getPlayerInTurn();

		if (player.getType() != Player.Type.COMPUTER) {
			throw new IllegalStateException();
		}

		Node move = player.getMoveStrategy().move(player.getId(), rules, board);

		return move(player.getId(), move.getId());
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
		if (!turnHandler.getPlayerInTurn().getId().equals(playerId)) {
			throw new IllegalArgumentException("Not this player's turn.");
		}

		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, nodeId);

		if (nodesToSwap.isEmpty()) {
			throw new IllegalArgumentException();
		}

		for (Node n : nodesToSwap) {
			setOccupantPlayer(n, playerId);
		}

		turnHandler.passTurnToNextPlayer();

		return nodesToSwap;
	}

	/**
	 * Sets the occupant player id of the node. The node must be an instance of NodeImpl, otherwise an exception is
	 * thrown.
	 * 
	 * @param node
	 *            Must be an instance of NodeImpl.
	 * @param playerId
	 *            The id of the new occupant player.
	 * @throws ClassCastException
	 *             if the node isn't an instance of NodeImpl.
	 */
	private void setOccupantPlayer(Node node, String playerId) {
		if (node instanceof NodeImpl) {
			NodeImpl nodeImpl = (NodeImpl) node;
			nodeImpl.setOccupantPlayer(playerId);
		} else {
			throw new ClassCastException();
		}
	}
}
