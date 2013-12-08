package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

class ClassicRules implements Rules {
	private Board board;

	public ClassicRules(Board board) {
		this.board = board;
	}

	/**
	 * Helper method to getNodesToSwap(). Returns a list of all nodes to swap in a single direction, when placedNode is
	 * placed on the board. Returns an empty list if no nodes should be swapped in this direction.
	 * 
	 * @param dx
	 *            Difference in x-coordinate each iteration
	 * @param dy
	 *            Difference in y-coordinate each iteration
	 */
	private List<Node> swapDirection(Node placedNode, int dx, int dy, String playerId) {
		List<Node> nodesToSwap = new ArrayList<>();

		int x = placedNode.getXCoordinate();
		int y = placedNode.getYCoordinate();

		while (true) {
			x += dx;
			y += dy;
			Node n = null;
			try {
				n = board.getNode(x, y);
			} catch (IllegalArgumentException iae) { // this is thrown if coordinates are outside of the board
				return new ArrayList<Node>();
			}
			// this null check shouldn't be necessary, it's however needed in order to be able to use this method with a
			// mocked board.
			if (n == null) {
				return new ArrayList<Node>();
			}
			if (!n.isMarked()) {
				return new ArrayList<Node>();
			} else if (!n.getOccupantPlayerId().equals(playerId)) { // this node contains an opponent
				nodesToSwap.add(n);
			} else { // this node contains this player
				return nodesToSwap;
			}
		}
	}

	private Node getNodeWithId(String nodeId) throws IllegalArgumentException {
		for (Node n : board.getNodes()) {
			if (nodeId.equals(n.getId())) {
				return n;
			}
		}

		throw new IllegalArgumentException();
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		if (playerId == null) {
			throw new NullPointerException("playerId is null");
		} else if (nodeId == null) {
			throw new NullPointerException("nodeId is null");
		}

		List<Node> nodesToSwap = new ArrayList<>();

		Node placedNode = getNodeWithId(nodeId);

		if (placedNode.isMarked()) { // invalid move
			return nodesToSwap;
		}

		// Add all swappable nodes in all directions
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				if (dx == 0 && dy == 0) {
					continue;
				}
				nodesToSwap.addAll(swapDirection(placedNode, dx, dy, playerId));
			}
		}

		if (!nodesToSwap.isEmpty()) { // don't forget to add the node that was placed
			nodesToSwap.add(placedNode);
		}

		return nodesToSwap;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		if (playerId == null) {
			throw new NullPointerException("playerId is null");
		}

		for (Node n : board.getNodes()) {
			if (!getNodesToSwap(playerId, n.getId()).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return !getNodesToSwap(playerId, nodeId).isEmpty();
	}
}
