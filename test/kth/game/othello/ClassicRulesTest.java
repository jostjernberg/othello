package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicRulesTest {
	TestUtil testUtil = new TestUtil();

	private Board mockedDefaultBoard(List<Player> players) {
		List<Node> nodes = new ArrayList<>();
		Board board = Mockito.mock(Board.class);

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				String nodeId = x + ":" + y;
				String playerId = null;

				if (x == 3 && y == 4 || x == 4 && y == 3) {
					playerId = players.get(0).getId();
				} else if (x == 3 && y == 3 || x == 4 && y == 4) {
					playerId = players.get(1).getId();
				}

				Node n = testUtil.mockNode(x, y, playerId, nodeId);
				nodes.add(n);
				Mockito.when(board.getNode(x, y)).thenReturn(n);
			}
		}

		Mockito.when(board.getNodes()).thenReturn(nodes);

		return board;
	}

	private boolean nodeHasCoords(Node n, int x, int y) {
		return n.getXCoordinate() == x && n.getYCoordinate() == y;
	}

	@Test
	public void getNodesToSwapSimpleHorizontalTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);

		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, board.getNode(5, 3).getId());

		Assert.assertEquals(2, nodesToSwap.size());

		if (nodeHasCoords(nodesToSwap.get(0), 5, 3) && nodeHasCoords(nodesToSwap.get(1), 4, 3)) {

		} else if (nodeHasCoords(nodesToSwap.get(1), 5, 3) && nodeHasCoords(nodesToSwap.get(0), 4, 3)) {

		} else {
			Assert.fail();
		}
	}

	@Test
	public void getNodesToSwapSimpleVerticalTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);

		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		List<Node> nodesToSwap = rules.getNodesToSwap(playerId, board.getNode(3, 5).getId());

		Assert.assertEquals(2, nodesToSwap.size());

		if (nodeHasCoords(nodesToSwap.get(0), 3, 5) && nodeHasCoords(nodesToSwap.get(1), 3, 4)) {

		} else if (nodeHasCoords(nodesToSwap.get(1), 3, 5) && nodeHasCoords(nodesToSwap.get(0), 3, 4)) {

		} else {
			Assert.fail();
		}
	}

	@Test
	public void shouldBeValidMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);
		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		Assert.assertTrue(rules.isMoveValid(playerId, board.getNode(5, 3).getId()));
		Assert.assertTrue(rules.isMoveValid(playerId, board.getNode(3, 5).getId()));
	}

	@Test
	public void shouldNotBeValidMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);
		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(4, 3).getId()));
		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(3, 3).getId()));
		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(2, 2).getId()));
	}

	@Test
	public void hasValidMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);

		Assert.assertTrue(rules.hasValidMove(board.getNode(3, 3).getOccupantPlayerId()));
		Assert.assertTrue(rules.hasValidMove(board.getNode(3, 4).getOccupantPlayerId()));
	}
}
