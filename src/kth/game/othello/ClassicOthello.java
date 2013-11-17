package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.InternalBoard;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class ClassicOthello implements Othello {
	private InternalBoard board;
	private List<Player> players;
	private int nextPlayerInTurnIndex;
	private OthelloState gameState = OthelloState.STARTUP;

	/**
	 * An othello game.
	 * 
	 * @param players
	 *            The players of the game.
	 * @param board
	 *            The board used for the game.
	 * @param boardWidth
	 *            Width of the board.
	 * @param boardHeight
	 *            Height of the board.
	 */
	public ClassicOthello(List<Player> players, InternalBoard board) {
		this.board = board;
		this.players = players;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	/** Checks whether or not a node is swappable for the specified player */
	private boolean isOpponent(Node n, String playerId) {
		return (n.isMarked() && !n.getOccupantPlayerId().equals(playerId));
	}

	/** Checks whether or not a node is owned by the specified player */
	private boolean isFriendly(Node n, String playerId) {
		return (n.isMarked() && n.getOccupantPlayerId().equals(playerId));
	}

	/**
	 * Add all valid nodes in a single direction.
	 * 
	 * @param dx
	 *            Difference in x-coordinate each iteration
	 * @param dy
	 *            Difference in y-coordinate each iteration
	 */
	private void swapDirection(int x, int y, int dx, int dy, String playerId, List<Node> nodesToSwap) {
		x += dx;
		y += dy;
		List<Node> potentialSwapNodes = new ArrayList<>();
		while (x < board.getColumns() && x >= 0 && y < board.getRows() && y >= 0) {
			Node n = getNode(x, y);
			if (isOpponent(n, playerId)) {
				potentialSwapNodes.add(n);
			} else {
				if (isFriendly(n, playerId)) {
					nodesToSwap.addAll(potentialSwapNodes);
				} // else: no swaps allowed in this direction
				break;
			}
			x += dx;
			y += dy;
		}
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> nodesToSwap = new ArrayList<>();
		Node placedNode = getNode(nodeId);

		if (placedNode.isMarked()) {
			// Invalid move
			return nodesToSwap;
		}

		int x = placedNode.getXCoordinate();
		int y = placedNode.getYCoordinate();

		// Add all swappable nodes in all directions
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) // not a direction
					continue;
				swapDirection(x, y, i, j, playerId, nodesToSwap);
			}
		}

		if (!nodesToSwap.isEmpty()) {
			nodesToSwap.add(getNode(nodeId));
		}

		return nodesToSwap;
	}

	@Override
	public Player getPlayerInTurn() {
		return players.get(nextPlayerInTurnIndex);
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		for (Node n : board.getNodes()) {
			if (!getNodesToSwap(playerId, n.getId()).isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Get node with coordinates (x, y).
	 */
	private Node getNode(int x, int y) {
		for (Node n : board.getNodes()) {
			if (n.getXCoordinate() == x && n.getYCoordinate() == y) {
				return n;
			}
		}
		throw new IllegalArgumentException("No node with coordinate (" + x + ", " + y + ").");
	}

	@Override
	public boolean isActive() {
		for (Player p : players) {
			if (hasValidMove(p.getId())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return getPlayerInTurn().getId().equals(playerId) && !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	/**
	 * Updates which player's turn it is.
	 */
	private void endTurn() {
		do {
			nextPlayerInTurnIndex = (nextPlayerInTurnIndex + 1) % players.size();
		} while (isActive() && !hasValidMove(getPlayerInTurn().getId()));
	}

	/**
	 * Get the Node if this game's board that has nodeId as id.
	 * 
	 * @param nodeId
	 *            Id of the requested Node.
	 * @return The node with the requested id.
	 * @throws IllegalArgumentException
	 *             If there is no node on this board with the requested id.
	 */
	private Node getNode(String nodeId) {
		for (Node n : board.getNodes()) {
			if (n.getId().equals(nodeId)) {
				return n;
			}
		}
		throw new IllegalArgumentException("There is no node with id '" + nodeId + "'.");
	}

	@Override
	public List<Node> move() {
		Player player = getPlayerInTurn();
		if (player.getType() != Type.COMPUTER) {
			throw new IllegalStateException("Next player is not a computer.");
		}

		for (Node n : board.getNodes()) {
			if (isMoveValid(player.getId(), n.getId())) {
				return move(player.getId(), n.getId());
			}
		}

		// pass turn if cannot make any moves
		endTurn();
		return new ArrayList<>();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);

		if (!nodesToSwap.isEmpty()) {
			for (Node n : nodesToSwap) {
				board.changeOccupant(n.getXCoordinate(), n.getYCoordinate(), playerId);
			}
		}
		endTurn();

		return nodesToSwap;
	}

	@Override
	public void start() {
		if (gameState != OthelloState.STARTUP) {
			throw new IllegalStateException("Cannot start a game which has already started.");
		}
		nextPlayerInTurnIndex = (int) (Math.random() * players.size());
		gameState = OthelloState.ACTIVE;
	}

	@Override
	public void start(String playerId) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(playerId)) {
				nextPlayerInTurnIndex = i;
				return;
			}
		}
		// use default value if there are no players with id = playerId.
	}

	/**
	 * Print player list and current board status.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Player p : players) {
			sb.append(p.getId()).append(": ").append(p.getName()).append("\n");
		}
		return sb.toString() + board;
	}
}
