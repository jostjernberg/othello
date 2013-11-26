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

public class RandomMoveStrategyTest {
	TestUtil testUtil = new TestUtil();

	@Test
	public void canMakeAMoveTest() {
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = testUtil.mockedDefaultBoard(players);
		Rules rules = Mockito.mock(Rules.class);
		List<Node> nodesToSwap = new ArrayList<>();
		nodesToSwap.add(board.getNode(3, 3));
		nodesToSwap.add(board.getNode(2, 4));
		Mockito.when(rules.isMoveValid(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);

		RandomMoveStrategy rms = RandomMoveStrategy.INSTANCE;

		Node move = rms.move(players.get(0).getId(), rules, board);
		Assert.assertNotEquals(null, move);
	}
}
