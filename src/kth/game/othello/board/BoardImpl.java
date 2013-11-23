package kth.game.othello.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The responsibility of this class is to hold and provide access to a set of nodes.
 */
public class BoardImpl implements Board {
	private List<Node> nodes;
	private Map<String, Character> playerIdToPrintableTag;

	BoardImpl(List<Node> nodes) {
		this.nodes = nodes;

		playerIdToPrintableTag = new HashMap<>();
		int i = 0;
		for (Node n : nodes) {
			if (n.isMarked() && !playerIdToPrintableTag.containsKey(n.getOccupantPlayerId())) {
				playerIdToPrintableTag.put(n.getOccupantPlayerId(), Integer.toString(i).charAt(0));
				i++;
			}
		}
	}

	public Character playerIdToPrintableTag(String playerId) {
		return playerIdToPrintableTag.get(playerId);
	}

	@Override
	public Node getNode(int x, int y) {
		for (Node n : nodes) {
			if (x == n.getXCoordinate() && y == n.getYCoordinate()) {
				return n;
			}
		}

		throw new IllegalArgumentException("Can't find node with coordinates (" + x + ", " + y + ")");
	}

	@Override
	public List<Node> getNodes() {
		return nodes;
	}

	private int getWidth() {
		int width = 0;

		for (Node n : getNodes()) {
			width = Math.max(width, n.getXCoordinate());
		}

		return width + 1;
	}

	private int getHeight() {
		int height = 0;

		for (Node n : getNodes()) {
			height = Math.max(height, n.getYCoordinate());
		}

		return height + 1;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int width = getWidth();
		int height = getHeight();

		sb.append("   ");
		for (int x = 0; x < width * 2 + 1; x++) {
			sb.append("_");
		}
		sb.append("\n");
		for (int y = height - 1; y >= 0; y--) {
			sb.append(y).append(" | ");
			for (int x = 0; x < width; x++) {
				char token = ' ';
				try {
					Node n = getNode(x, y);
					if (n.isMarked()) {
						token = playerIdToPrintableTag.get(n.getOccupantPlayerId());
					} else {
						token = '.';
					}
				} catch (IllegalArgumentException iae) {
				}
				sb.append(token).append(" ");
			}
			sb.append("|\n");
		}

		sb.append("   ");
		for (int x = 0; x < width * 2 + 1; x++) {
			sb.append("-");
		}
		sb.append("\n").append("    ");
		for (int x = 0; x < width; x++) {
			sb.append(x).append(" ");
		}

		return sb.toString();
	}
}
