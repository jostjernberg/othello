package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeImpl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class ClassicScoreCountingStrategyTest {

	private Node mockNode(String occupantPlayerId) {
		Node n = Mockito.mock(NodeImpl.class);
		boolean isMarked = occupantPlayerId != null;

		Mockito.when(n.getOccupantPlayerId()).thenReturn(occupantPlayerId);
		Mockito.when(n.isMarked()).thenReturn(isMarked);

		return n;
	}

	@Test
	public void getPlayersScoreTest() {
		// mock setup
		List<Node> nodes = new ArrayList<>();
		nodes.add(mockNode(null));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode(null));
		Board board = Mockito.mock(BoardImpl.class);
		Mockito.when(board.getNodes()).thenReturn(nodes);

		// unit setup
		ClassicScoreCountingStrategy strat = ClassicScoreCountingStrategy.INSTANCE;
		List<ScoreItem> scoreItems = strat.getPlayersScore(board);

		// asserts
		Assert.assertEquals(2, scoreItems.size());
		Assert.assertEquals("player2", scoreItems.get(0).getPlayerId());
		Assert.assertEquals(5, scoreItems.get(0).getScore());
		Assert.assertEquals("player1", scoreItems.get(1).getPlayerId());
		Assert.assertEquals(3, scoreItems.get(1).getScore());
	}

	@Test
	public void getPointsCorrectAnswerTest() {
		// mock setup
		List<Node> nodes = new ArrayList<>();
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player2"));
		nodes.add(mockNode("player1"));
		Board board = Mockito.mock(BoardImpl.class);
		Mockito.when(board.getNodes()).thenReturn(nodes);

		// unit setup
		ClassicScoreCountingStrategy strat = ClassicScoreCountingStrategy.INSTANCE;

		// asserts
		Assert.assertEquals(6, strat.getPoints(board, "player1"));
		Assert.assertEquals(7, strat.getPoints(board, "player2"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void getPointThrowsExceptionWhenPlayerNotPresentTest() {
		List<Node> nodes = new ArrayList<>();
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode(null));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode("player1"));
		nodes.add(mockNode(null));
		Board board = Mockito.mock(BoardImpl.class);
		Mockito.when(board.getNodes()).thenReturn(nodes);

		ClassicScoreCountingStrategy strat = ClassicScoreCountingStrategy.INSTANCE;
		strat.getPoints(board, "player2"); // should throw exception since player2 not present on board
	}
}
