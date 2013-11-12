package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

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
		if (!getPlayerInTurn().getId().equals(playerId)) {
			throw new IllegalArgumentException("This turn is player with id " + getPlayerInTurn().getId()
					+ "'s turn, not player with id " + playerId + "'s turn.");
		}

		List<Node> nodesToSwap = new ArrayList<Node>();
		Node placedNode = getNode(nodeId);
		if (!placedNode.isMarked()) {
			return nodesToSwap;
		}

		// Add all swappable nodes to the right
		for (int x = placedNode.getXCoordinate(); x < widthOf(board); x++) {
			Node currNode = getNode(x, placedNode.getYCoordinate());
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes to the left
		for (int x = placedNode.getXCoordinate(); x >= 0; x--) {
			Node currNode = getNode(x, placedNode.getYCoordinate());
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes above
		for (int y = placedNode.getYCoordinate(); y < heightOf(board); y++) {
			Node currNode = getNode(placedNode.getXCoordinate(), y);
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
		}

		// Add all swappable nodes below
		for (int y = placedNode.getYCoordinate(); y >= 0; y--) {
			Node currNode = getNode(placedNode.getXCoordinate(), y);
			if (!currNode.isMarked() || currNode.getOccupantPlayerId().equals(playerId)) {
				break;
			}
			nodesToSwap.add(currNode);
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
		return !getNodesToSwap(playerId, nodeId).isEmpty();
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
		Player player = players.get(nextPlayerInTurnIndex);
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
		if (!players.get(nextPlayerInTurnIndex).getId().equals(playerId)) {
			throw new IllegalArgumentException("Not this player's turn.");
		} else if (!isMoveValid(playerId, nodeId)) {
			throw new IllegalArgumentException("Invalid move.");
		}

		List<Node> nodesToSwap = getNodesToSwap(playerId, nodeId);
		List<Node> nodes = replaceNodesThatHaveSameCoordinatesWithNewPlayerId(board.getNodes(), nodesToSwap, playerId);

		board = new ClassicBoard(nodes);
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
	 * Returns a new list of nodes where the nodes in originalNodes that have the same coordinates as the nodes in
	 * replacementNodes have been replaced by new nodes with the original coordinates, but with the player that have
	 * replacementPlayerIdas id as the new occupant player.
	 * 
	 * @param originalNodes
	 *            The original list of nodes.
	 * @param replacementNodes
	 *            The nodes which will replace other nodes in the original list.
	 * @param replacementPlayerId
	 *            The new player id of the nodes to be replaced.
	 * @return A modified version of the original node list, where nodes that have the same (x, y)-coordinates have been
	 *         replaced by new nodes.
	 */
	private List<Node> replaceNodesThatHaveSameCoordinatesWithNewPlayerId(List<Node> originalNodes,
			List<Node> replacementNodes, String replacementPlayerId) {
		List<Node> ret = new ArrayList<Node>(originalNodes.size());

		for (int i = 0; i < originalNodes.size(); i++) {
			Node nextToAdd = null;
			for (int j = 0; j < replacementNodes.size(); j++) {
				if (originalNodes.get(i).getXCoordinate() == replacementNodes.get(j).getXCoordinate()
						&& originalNodes.get(i).getYCoordinate() == replacementNodes.get(j).getYCoordinate()) {
					nextToAdd = new ClassicNode(replacementNodes.get(j).getYCoordinate(), replacementNodes.get(j)
							.getXCoordinate(), getPlayer(replacementPlayerId));
				}
			}
			if (nextToAdd == null) {
				nextToAdd = originalNodes.get(i);
			}
			ret.add(nextToAdd);
		}

		return ret;
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
}
