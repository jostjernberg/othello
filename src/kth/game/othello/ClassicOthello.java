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
	private List<Player> players;
	private int nextPlayerInTurnIndex;

	/**
	 * An othello game.
	 * 
	 * @param board
	 *            The board used for the game.
	 * @param players
	 *            The players of the game.
	 */
	public ClassicOthello(Board board, List<Player> players) {
		this.board = board;
		this.players = players;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		List<Node> nodesToSwap = new ArrayList<Node>();
		Node placedNode = getNode(nodeId);
		if (placedNode.isMarked()) {
			return nodesToSwap;
		}

		// Add all swappable nodes to the right
		for (int x = placedNode.getXCoordinate() + 1; x < widthOf(board); x++) {
			Node currNode = getNode(x, placedNode.getYCoordinate());
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes to the left
		for (int x = placedNode.getXCoordinate() - 1; x >= 0; x--) {
			Node currNode = getNode(x, placedNode.getYCoordinate());
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes above
		for (int y = placedNode.getYCoordinate() + 1; y < heightOf(board); y++) {
			Node currNode = getNode(placedNode.getXCoordinate(), y);
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes below
		for (int y = placedNode.getYCoordinate() - 1; y >= 0; y--) {
			Node currNode = getNode(placedNode.getXCoordinate(), y);
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
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

	private int widthOf(Board board) {
		int width = 0;
		for (Node n : board.getNodes()) {
			if (n.getXCoordinate() > width) {
				width = n.getXCoordinate();
			}
		}
		return width + 1;
	}

	private int heightOf(Board board) {
		int height = 0;
		for (Node n : board.getNodes()) {
			if (n.getYCoordinate() > height) {
				height = n.getYCoordinate();
			}
		}
		return height + 1;
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
			if (!hasValidMove(p.getId())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return getPlayerInTurn().getId().equals(playerId) && !getNodesToSwap(playerId, nodeId).isEmpty();
	}

	/**
	 * Get the Player that has playerId as id.
	 * 
	 * @param playerId
	 *            Id of the requested Player.
	 * @return The player with the requested id.
	 * @throws IllegalArgumentException
	 *             If there is no player with the requested id.
	 */
	private Player getPlayer(String playerId) {
		for (Player p : players) {
			if (p.getId().equals(playerId)) {
				return p;
			}
		}
		throw new IllegalArgumentException("There is no player with id '" + playerId + "'.");
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
				List<Node> nodesToSwap = getNodesToSwap(player.getId(), n.getId());
				if (nodesToSwap != null) {
					return move(player.getId(), nodesToSwap.get(0).getId());
				}
			}
		}

		// this should never happen
		return null;
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
	public static List<Node> replaceNodes(List<Node> originalNodes, List<Node> replacementNodes, String playerId) {
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
		nextPlayerInTurnIndex = (int) (Math.random() * (players.size() + 1));
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
			playerNumber.put(players.get(i).getId(), (char) i);
		}

		for (Node n : board.getNodes()) {
			char sign = '.';
			if (n.isMarked()) {
				sign = playerNumber.get(n.getOccupantPlayerId());
			}
			visualBoard[n.getXCoordinate()][n.getYCoordinate()] = sign;
		}

		for (int y = 0; y < 8; y++) {
			sb.append(y).append(" ");
			for (int x = 0; x < 8; x++) {
				sb.append(visualBoard[y][x]).append(" ");
			}
			sb.append("\n");
		}
		sb.append("  ");
		for (int i = 0; i < 8; i++) {
			sb.append(i).append(" ");
		}
		sb.append("\n");

		return sb.toString();
	}
}
