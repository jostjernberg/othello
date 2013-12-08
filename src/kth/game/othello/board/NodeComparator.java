package kth.game.othello.board;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	/**
	 * Compares the coordinates of two nodes. Returns:
	 * 0 if both nodes have the same x- and y-coordinates
	 * 1 if n1 has a higher y-coordinate than n2 or if n1 has the same y-coordinate than n2, but a higher x-coordinate
	 * -1 otherwise
	 */
	@Override
	public int compare(Node n1, Node n2) {
		int y1 = n1.getYCoordinate();
		int x1 = n1.getXCoordinate();
		int y2 = n2.getYCoordinate();
		int x2 = n2.getXCoordinate();
		if (y1 > y2 || y1 == y2 && x1 > x2) {
			return 1;
		} else if (y1 < y2 || y1 == y2 && x1 < x2) {
			return -1;
		} else {
			return 0;
		}
	}
}
