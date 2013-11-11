package kth.game.othello.board;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

	@Test
	public void defaultSizedClassicBoardCreation() {
		Board b = new ClassicBoard();
		nodesAlignedCorrectly(b.getNodes(), ClassicBoard.DEFAULT_ROWS, ClassicBoard.DEFAULT_ROWS);
	}

	@Test
	public void customSizedClassicBoardCreation() {
		int rows = 17, cols = 3;
		Board b = new ClassicBoard(rows, cols);
		nodesAlignedCorrectly(b.getNodes(), rows, cols);
	}

	private void nodesAlignedCorrectly(List<Node> nodes, int rows, int cols) {
		Iterator<Node> nodeIt = nodes.iterator();
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				Assert.assertTrue(nodeIt.hasNext());
				Node n = nodeIt.next();
				Assert.assertEquals(n.getXCoordinate(), x);
				Assert.assertEquals(n.getYCoordinate(), y);
			}
		}
	}
}
