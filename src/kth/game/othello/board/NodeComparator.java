package kth.game.othello.board;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

	/**
	 * Compare two nodes by looking at first comparing y-coordinate and then
	 * x-coordinate.
	 * 
	 * @return 1 if n1 has a higher coordinate value than n2, -1 otherwise. 0
	 *         implies that a duplicate node illegally exist.
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
