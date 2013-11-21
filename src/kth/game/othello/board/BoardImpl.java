package kth.game.othello.board;

import java.util.List;

class BoardImpl implements Board {
	private List<Node> nodes;

	@Override
	public Node getNode(int x, int y) {
		Node node = null;

		for (Node n : nodes) {
			if (x == n.getXCoordinate() && y == n.getYCoordinate()) {
				node = n;
				break;
			}
		}

		return node;
	}

	@Override
	public List<Node> getNodes() {
		return nodes;
	}

}
