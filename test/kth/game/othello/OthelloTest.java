package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.InternalBoard;
import kth.game.othello.board.Node;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class OthelloTest {

	@Test
	public void getNodesToSwapTest() {
		Othello othello = mockedDefaultClassicOthello();

		Node lowerLeftPiece = getNode(othello, 3, 3);

		String playerId = lowerLeftPiece.getOccupantPlayerId();
		Assert.assertTrue(playerId != null);

		othello.start(playerId);

		Node nodeToPlace = getNode(othello, 5, 3);

		List<Node> nodesToSwap = othello.getNodesToSwap(playerId, nodeToPlace.getId());

		Assert.assertEquals(nodesToSwap.size(), 2);
		Assert.assertEquals(nodesToSwap.get(0).getXCoordinate(), 4);
		Assert.assertEquals(nodesToSwap.get(0).getYCoordinate(), 3);

		Assert.assertEquals(nodesToSwap.get(1).getXCoordinate(), 5);
		Assert.assertEquals(nodesToSwap.get(1).getYCoordinate(), 3);
	}

	private Node getNode(Othello othello, int x, int y) {
		return getNode(othello.getBoard(), x, y);
	}

	private Node getNode(Board board, int x, int y) {
		return getNode(board.getNodes(), x, y);
	}

	private Node getNode(List<Node> nodes, int x, int y) {
		for (Node n : nodes) {
			if (n.getXCoordinate() == x && n.getYCoordinate() == y) {
				return n;
			}
		}
		throw new IllegalArgumentException("Node not found.");
	}

	@Test
	public void getBoardTest() {
		Othello othello = mockedDefaultClassicOthello();
		Board board = mockedDefaultBoard(othello.getPlayers());

		for (int i = 0; i < board.getNodes().size(); i++) {
			Node fromBoard = board.getNodes().get(i);
			Node fromOthello = othello.getBoard().getNodes().get(i);
			Assert.assertEquals(fromBoard.getId(), fromOthello.getId());
			Assert.assertEquals(fromBoard.getXCoordinate(), fromOthello.getXCoordinate());
			Assert.assertEquals(fromBoard.getYCoordinate(), fromOthello.getYCoordinate());
			Assert.assertEquals(fromBoard.getOccupantPlayerId(), fromOthello.getOccupantPlayerId());
		}
	}

	@Test
	public void getPlayersTest() {
		Othello othello = mockedDefaultClassicOthello();
		Assert.assertEquals(othello.getPlayers().size(), 2);
		Assert.assertEquals(othello.getPlayers().get(0).getId(), "id1");
		Assert.assertEquals(othello.getPlayers().get(1).getId(), "id2");
	}

	private InternalBoard mockedDefaultBoard(List<Player> players) {
		List<Node> nodes = new ArrayList<>();

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Node n = Mockito.mock(ClassicNode.class);
				Mockito.when(n.getXCoordinate()).thenReturn(x);
				Mockito.when(n.getYCoordinate()).thenReturn(y);
				Mockito.when(n.getId()).thenReturn(Integer.toHexString(200000 + y * 8 + x));
				if (x == 3 && y == 4 || x == 4 && y == 3) {
					String playerId = players.get(0).getId();
					Mockito.when(n.getOccupantPlayerId()).thenReturn(playerId);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else if (x == 3 && y == 3 || x == 4 && y == 4) {
					String playerId = players.get(1).getId();
					Mockito.when(n.getOccupantPlayerId()).thenReturn(playerId);
					Mockito.when(n.isMarked()).thenReturn(true);
				} else {
					Mockito.when(n.getOccupantPlayerId()).thenReturn(null);
					Mockito.when(n.isMarked()).thenReturn(false);
				}
				nodes.add(n);
			}
		}

		InternalBoard board = Mockito.mock(ClassicBoard.class);
		Mockito.when(board.getNodes()).thenReturn(nodes);
		Mockito.when(board.getColumns()).thenReturn(8);
		Mockito.when(board.getRows()).thenReturn(8);

		return board;
	}

	private Othello mockedDefaultClassicOthello() {
		List<Player> players = new ArrayList<>();

		Player p1 = Mockito.mock(HumanPlayer.class);
		Player p2 = Mockito.mock(HumanPlayer.class);

		Mockito.when(p1.getId()).thenReturn("id1");
		Mockito.when(p2.getId()).thenReturn("id2");

		players.add(p1);
		players.add(p2);

		InternalBoard board = mockedDefaultBoard(players);

		return new ClassicOthello(players, board);
	}

	@Test
	public void hasValidMoveTest() {
		Othello othello = mockedDefaultClassicOthello();

		Assert.assertTrue(othello.hasValidMove(othello.getPlayerInTurn().getId()));
	}

	@Test
	public void isActiveTest() {
		Othello othello = mockedDefaultClassicOthello();

		Assert.assertTrue(othello.isActive());
	}

	@Test
	public void isMoveValidTest() {
		Othello othello = mockedDefaultClassicOthello();

		String playerId = getNode(othello, 3, 3).getOccupantPlayerId();
		othello.start(playerId);

		Assert.assertFalse(othello.isMoveValid(playerId, getNode(othello, 3, 3).getId()));
		Assert.assertFalse(othello.isMoveValid(playerId, getNode(othello, 1, 1).getId()));
		Assert.assertFalse(othello.isMoveValid(playerId, getNode(othello, 3, 4).getId()));

		Assert.assertTrue(othello.isMoveValid(playerId, getNode(othello, 5, 3).getId()));
		Assert.assertTrue(othello.isMoveValid(playerId, getNode(othello, 3, 5).getId()));
	}

	// TODO: move to integration test?
	// @Test
	// public void moveTest() {
	// Othello othello = mockedDefaultClassicOthello();
	//
	// String playerId = getNode(othello, 3, 3).getOccupantPlayerId();
	// othello.start(playerId);
	//
	// System.out.println(othello);
	// othello.move(playerId, getNode(othello, 5, 3).getId());
	// System.out.println(othello);
	//
	// Assert.assertNotEquals(playerId, othello.getPlayerInTurn().getId());
	// Assert.assertEquals(getNode(othello, 5, 3).getOccupantPlayerId(),
	// playerId);
	// Assert.assertEquals(getNode(othello, 4, 3).getOccupantPlayerId(),
	// playerId);
	// Assert.assertEquals(getNode(othello, 3, 3).getOccupantPlayerId(),
	// playerId);
	// Assert.assertEquals(getNode(othello, 4, 4).getOccupantPlayerId(),
	// playerId);
	// Assert.assertNotEquals(getNode(othello, 3, 4).getOccupantPlayerId(),
	// playerId);
	// }

	@Test
	public void startTest() {
		Othello othello = mockedDefaultClassicOthello();

		String playerId = null;
		if (othello.getPlayerInTurn().getId().equals(othello.getPlayers().get(0).getId())) {
			playerId = othello.getPlayers().get(1).getId();
		} else {
			playerId = othello.getPlayers().get(0).getId();
		}

		Assert.assertNotEquals(playerId, othello.getPlayerInTurn().getId());

		othello.start(playerId);

		Assert.assertEquals(playerId, othello.getPlayerInTurn().getId());
	}
}
