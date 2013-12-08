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

public class GreedyWorstMoveStrategyTest {
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
		Mockito.when(rules.isMoveValid(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
		
		GreedyWorstMoveStrategy gwms = GreedyWorstMoveStrategy.INSTANCE;

		Node move = gwms.move(players.get(0).getId(), rules, board);
		Assert.assertNotEquals(null, move);
	}

	@Test
	public void makesCorrectMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = testUtil.mockedDefaultBoard(players);
		Rules rules = Mockito.mock(Rules.class);
		List<Node> threeNodesToSwap = new ArrayList<>();
		threeNodesToSwap.add(board.getNode(3, 3));
		threeNodesToSwap.add(board.getNode(2, 4));
		threeNodesToSwap.add(board.getNode(1, 5));
		Mockito.when(rules.isMoveValid(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
		Mockito.when(rules.getNodesToSwap(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(
				threeNodesToSwap);
		List<Node> twoNodesToSwap = new ArrayList<>();
		twoNodesToSwap.add(board.getNode(4, 5));
		twoNodesToSwap.add(board.getNode(7, 6));
		String playerId = players.get(0).getId();
		String nodeId = board.getNode(5, 5).getId();
		Mockito.when(rules.getNodesToSwap(playerId, nodeId)).thenReturn(twoNodesToSwap);

		GreedyWorstMoveStrategy gwms = GreedyWorstMoveStrategy.INSTANCE;

		Node move = gwms.move(players.get(0).getId(), rules, board);
		Assert.assertNotEquals(null, move);
		Assert.assertEquals(nodeId, move.getId());
	}
}
