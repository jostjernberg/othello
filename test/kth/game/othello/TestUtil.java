package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;
import kth.game.othello.player.Player;

import org.mockito.Mockito;

public class TestUtil {

	public List<Player> mockedDefaultPlayers() {
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

	public Node mockNode(int x, int y, String occupantPlayerId, String nodeId) {
		Node n = Mockito.mock(NodeImpl.class);
		boolean isMarked = occupantPlayerId != null;

		Mockito.when(n.getXCoordinate()).thenReturn(x);
		Mockito.when(n.getYCoordinate()).thenReturn(y);
		Mockito.when(n.getId()).thenReturn(nodeId);
		Mockito.when(n.getOccupantPlayerId()).thenReturn(occupantPlayerId);
		Mockito.when(n.isMarked()).thenReturn(isMarked);

		return n;
	}

	public Board mockedDefaultBoard(List<Player> players) {
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

}
