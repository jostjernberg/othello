package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicRulesTest {

	private List<Player> mockedDefaultPlayers() {
		List<Player> players = new ArrayList<>();

		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);

		Mockito.when(p1.getName()).thenReturn("player1name");
		Mockito.when(p2.getName()).thenReturn("player2name");

		Mockito.when(p1.getId()).thenReturn("player1id");
		Mockito.when(p2.getId()).thenReturn("player2id");

		players.add(p1);
		players.add(p2);

		return players;
	}

	private Node mockNode(int x, int y, String occupantPlayerId, String nodeId) {
		Node n = Mockito.mock(NodeImpl.class);
		boolean isMarked = occupantPlayerId != null;

		Mockito.when(n.getXCoordinate()).thenReturn(x);
		Mockito.when(n.getYCoordinate()).thenReturn(y);
		Mockito.when(n.getId()).thenReturn(nodeId);
		Mockito.when(n.getOccupantPlayerId()).thenReturn(occupantPlayerId);
		Mockito.when(n.isMarked()).thenReturn(isMarked);

		return n;
	}

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

				Node n = mockNode(x, y, playerId, nodeId);
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
		List<Player> players = mockedDefaultPlayers();
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
		List<Player> players = mockedDefaultPlayers();
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
		List<Player> players = mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);
		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		Assert.assertTrue(rules.isMoveValid(playerId, board.getNode(5, 3).getId()));
		Assert.assertTrue(rules.isMoveValid(playerId, board.getNode(3, 5).getId()));
	}

	@Test
	public void shouldNotBeValidMoveTest() {
		List<Player> players = mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);
		String playerId = board.getNode(3, 3).getOccupantPlayerId();

		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(4, 3).getId()));
		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(3, 3).getId()));
		Assert.assertFalse(rules.isMoveValid(playerId, board.getNode(2, 2).getId()));
	}

	@Test
	public void hasValidMoveTest() {
		List<Player> players = mockedDefaultPlayers();
		Board board = mockedDefaultBoard(players);

		Rules rules = new ClassicRules(board);

		Assert.assertTrue(rules.hasValidMove(board.getNode(3, 3).getOccupantPlayerId()));
		Assert.assertTrue(rules.hasValidMove(board.getNode(3, 4).getOccupantPlayerId()));
	}
}
