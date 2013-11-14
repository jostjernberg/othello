package kth.game.othello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

public class ClassicOthello implements Othello {
	private Board board;
	private final int boardWidth;
	private final int boardHeight;

	private List<Player> players;
	private int nextPlayerInTurnIndex;

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
	public ClassicOthello(List<Player> players, Board board, int boardWidth, int boardHeight) {
		this.board = board;
		this.players = players;
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
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
	private List<Node> swapDirection(int x, int y, int dx, int dy, String playerId, List<Node> nodesToSwap) {
		x += dx;
		y += dy;
		List<Node> potentialSwapNodes = new ArrayList<>();
		while (x < boardWidth && x >= 0 && y < boardHeight && y >= 0) {
			Node n = getNode(x, y);
			if (isOpponent(n, playerId)) {
				potentialSwapNodes.add(n);
			} else {
				if (isFriendly(n, playerId)) {
					return potentialSwapNodes;
				}
				break;
			}
			x += dx;
			y += dy;
		}
		return null;
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
				List<Node> swapNodes = swapDirection(x, y, i, j, playerId, nodesToSwap);
				if (swapNodes != null) {
					for (Node n : swapNodes) {
						nodesToSwap.add(n);
					}
				}
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

		throw new IllegalStateException(getPlayerInTurn() + " cannot make a move.");
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);

		List<Node> newNodes = replaceNodes(board.getNodes(), nodesToSwap, playerId);

		board = new ClassicBoard(newNodes);

		endTurn();

		return nodesToSwap;
	}

	/**
	 * Updates which player's turn it is.
	 */
	private void endTurn() {
		nextPlayerInTurnIndex = (nextPlayerInTurnIndex + 1) % players.size();
	}

	/**
	 * Immutable. Creates a new list consisting of originalNodes, where the nodes that have the same coordinates as
	 * nodes in replacementNodes have been replaced by new nodes with the same coordinates, but with playerId as
	 * occupantPlayerId.
	 * 
	 * @param originalNodes
	 *            The original list of nodes.
	 * @param replacementNodes
	 *            Nodes that will be replaced in originalNodes where the coordinates are equal.
	 * @param playerId
	 *            The replacement occupantPlayerId of the nodes contained in replacementNodes.
	 * @return A new list where some nodes from originalNodes have been replaced.
	 */
	private List<Node> replaceNodes(List<Node> originalNodes, List<Node> replacementNodes, String playerId) {
		List<Node> newNodes = new ArrayList<Node>(originalNodes.size());

		for (Node n : originalNodes) {
			newNodes.add(n);
		}

		for (Node n : replacementNodes) {
			newNodes = replaceNode(newNodes, new ClassicNode(n.getXCoordinate(), n.getYCoordinate(), playerId));
		}

		return newNodes;
	}

	/**
	 * Immutable. Returns a new list that is a copy of nodes, but where the node that have the same coordinates as
	 * replacement has been replaced by replacement.
	 * 
	 * @param nodes
	 *            The original list where one node will be replaced.
	 * @param replacement
	 *            The node which will be replaced in the original list.
	 * @return A new list where replacement has been inserted.
	 */
	private static List<Node> replaceNode(List<Node> nodes, Node replacement) {
		List<Node> replacedNodes = new ArrayList<Node>(nodes.size());

		for (int i = 0; i < nodes.size(); i++) {
			int x = nodes.get(i).getXCoordinate();
			int y = nodes.get(i).getYCoordinate();

			if (x == replacement.getXCoordinate() && y == replacement.getYCoordinate()) {
				replacedNodes.add(replacement);
			} else {
				replacedNodes.add(nodes.get(i));
			}
		}
		return replacedNodes;
	}

	@Override
	public void start() {
		nextPlayerInTurnIndex = (int) (Math.random() * players.size());
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		char[][] visualBoard = new char[8][8];

		Map<String, Character> playerNumber = new HashMap<>();

		for (int i = 0; i < players.size(); i++) {
			playerNumber.put(players.get(i).getId(), Integer.toString(i).charAt(0));
			sb.append(i).append(": ").append(players.get(i).getName()).append("\n");
		}

		sb.append("Player ").append(getPlayerInTurn().getName()).append("'s turn.\n");

		for (Node n : board.getNodes()) {
			char sign = '.';
			if (n.isMarked()) {
				sign = playerNumber.get(n.getOccupantPlayerId());
			}
			visualBoard[n.getXCoordinate()][n.getYCoordinate()] = sign;
		}

		sb.append("   ");
		for (int i = 0; i < boardWidth * 2 + 1; i++) {
			sb.append("_");
		}
		sb.append("\n");
		for (int y = boardHeight - 1; y >= 0; y--) {
			sb.append(y).append(" | ");
			for (int x = 0; x < boardWidth; x++) {
				sb.append(visualBoard[y][x]).append(" ");
			}
			sb.append("|\n");
		}
		sb.append("  |");
		for (int i = 0; i < boardWidth; i++) {
			sb.append("__");
		}
		sb.append("_|\n");
		sb.append("    ");
		for (int i = 0; i < boardWidth; i++) {
			sb.append(i).append(" ");
		}
		sb.append("\n");

		return sb.toString();
	}
}
