package kth.game.othello.player.movestrategy;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Rules;
import kth.game.othello.TestUtil;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GreedyMoveStrategyTest {
	TestUtil testUtil = new TestUtil();

	@Test
	public void canMakeAMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = testUtil.mockedDefaultBoard(players);
		Rules rules = Mockito.mock(Rules.class);
		List<Node> nodesToSwap = new ArrayList<>();
		nodesToSwap.add(board.getNode(3, 3));
		nodesToSwap.add(board.getNode(2, 4));
		Mockito.when(rules.getNodesToSwap(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(nodesToSwap);

		GreedyMoveStrategy rms = GreedyMoveStrategy.INSTANCE;

		Node move = rms.move(players.get(0).getId(), rules, board);
		Assert.assertNotEquals(null, move);
	}

	@Test
	public void makesCorrectMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = testUtil.mockedDefaultBoard(players);
		Rules rules = Mockito.mock(Rules.class);
		List<Node> nodesToSwap = new ArrayList<>();
		nodesToSwap.add(board.getNode(3, 3));
		nodesToSwap.add(board.getNode(2, 4));
		Mockito.when(rules.getNodesToSwap(Mockito.any(String.class), Mockito.any(String.class)))
				.thenReturn(nodesToSwap);
		List<Node> bestNodesToSwap = new ArrayList<>();
		bestNodesToSwap.add(board.getNode(4, 5));
		bestNodesToSwap.add(board.getNode(7, 6));
		bestNodesToSwap.add(board.getNode(2, 2));
		String playerId = players.get(0).getId();
		String nodeId = board.getNode(5, 5).getId();
		Mockito.when(rules.getNodesToSwap(playerId, nodeId)).thenReturn(bestNodesToSwap);

		GreedyMoveStrategy rms = GreedyMoveStrategy.INSTANCE;

		Node move = rms.move(players.get(0).getId(), rules, board);
		Assert.assertNotEquals(null, move);
		Assert.assertEquals(nodeId, move.getId());
	}
}
