package kth.game.othello;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;

public class OthelloFactoryTest {

	private boolean isConsistentClassicalBoard(Board board) {
		if (board.getNodes().get(3 * 8 + 4).getOccupantPlayerId() == null) {
			return false;
		} else if (!board.getNodes().get(3 * 8 + 4).getOccupantPlayerId()
				.equals(board.getNodes().get(4 * 8 + 3).getOccupantPlayerId())) {
			return false;
		} else if (board.getNodes().get(4 * 8 + 4).getOccupantPlayerId() == null) {
			return false;
		} else if (!board.getNodes().get(4 * 8 + 4).getOccupantPlayerId()
				.equals(board.getNodes().get(3 * 8 + 3).getOccupantPlayerId())) {
			return false;
		}

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if ((c != 3 && r != 3) && (c != 3 && r != 4) && (c != 4 && r != 3) && (c != 4 && r != 4)) {
					if (board.getNodes().get(r * 8 + c).getOccupantPlayerId() != null) {
						return false;
					}
				}
			}
		}

		return true;
	}

	@Test
	public void createComputerGameOnClassicalBoardTest() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		Assert.assertEquals(2, othello.getPlayers().size());
		Assert.assertEquals(Player.Type.COMPUTER, othello.getPlayers().get(0).getType());
		Assert.assertEquals(Player.Type.COMPUTER, othello.getPlayers().get(1).getType());
		Assert.assertTrue(isConsistentClassicalBoard(othello.getBoard()));
	}

	@Test
	public void createHumanGameOnOriginalBoardTest() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();
		Assert.assertEquals(2, othello.getPlayers().size());
		Assert.assertEquals(Player.Type.HUMAN, othello.getPlayers().get(0).getType());
		Assert.assertEquals(Player.Type.HUMAN, othello.getPlayers().get(1).getType());
		Assert.assertTrue(isConsistentClassicalBoard(othello.getBoard()));
	}

	@Test
	public void createHumanVersusComputerGameOnOriginalBoardTest() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		Assert.assertEquals(2, othello.getPlayers().size());
		if (othello.getPlayers().get(0).getType() == Player.Type.COMPUTER) {
			Assert.assertEquals(Player.Type.HUMAN, othello.getPlayers().get(1).getType());
		} else if (othello.getPlayers().get(0).getType() == Player.Type.HUMAN) {
			Assert.assertEquals(Player.Type.COMPUTER, othello.getPlayers().get(1).getType());
		} else {
			Assert.fail();
		}
		Assert.assertTrue(isConsistentClassicalBoard(othello.getBoard()));
	}
}
