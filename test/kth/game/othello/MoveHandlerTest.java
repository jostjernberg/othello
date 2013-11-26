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

public class MoveHandlerTest {
	TestUtil testUtil = new TestUtil();

	@Test
	public void simpleMoveTest() {
		// setup
		List<Player> players = testUtil.mockedDefaultPlayers();
		Board board = testUtil.mockedDefaultBoard(players);
		Rules rules = Mockito.mock(Rules.class);

		String playerId = board.getNode(3, 3).getOccupantPlayerId();
		Player player = testUtil.playerWithId(players, playerId);

		TurnHandler turnHandler = Mockito.mock(TurnHandler.class);
		Mockito.when(turnHandler.getPlayerInTurn()).thenReturn(player);

		List<Node> nodesToSwap = new ArrayList<>();
		nodesToSwap.add(board.getNode(4, 3));
		nodesToSwap.add(board.getNode(5, 3));

		Mockito.when(rules.getNodesToSwap(playerId, board.getNode(5, 3).getId())).thenReturn(nodesToSwap);

		MoveHandler moveHandler = new MoveHandler(board, rules, turnHandler);

		// perform
		List<Node> swappedNodes = moveHandler.move(playerId, board.getNode(5, 3).getId());

		// asserts
		Assert.assertEquals(2, swappedNodes.size());
		NodeImpl n1 = (NodeImpl) board.getNode(4, 3);
		NodeImpl n2 = (NodeImpl) board.getNode(5, 3);
		Mockito.verify(n1).setOccupantPlayer(playerId);
		Mockito.verify(n2).setOccupantPlayer(playerId);
		Mockito.verify(turnHandler).passTurnToNextPlayer();
	}

	@Test(expected = IllegalStateException.class)
	public void throwsExceptionWhenNotComputerPlayerInTurnTest() {
		Player player = Mockito.mock(Player.class);
		Mockito.when(player.getType()).thenReturn(Player.Type.HUMAN);
		TurnHandler turnHandler = Mockito.mock(TurnHandler.class);
		Mockito.when(turnHandler.getPlayerInTurn()).thenReturn(player);

		MoveHandler moveHandler = new MoveHandler(null, null, turnHandler);

		moveHandler.move();
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsExceptionWhenNotRightPlayerInTurnTest() {
		Player player = Mockito.mock(Player.class);
		Mockito.when(player.getId()).thenReturn("playerId1");
		TurnHandler turnHandler = Mockito.mock(TurnHandler.class);
		Mockito.when(turnHandler.getPlayerInTurn()).thenReturn(player);

		MoveHandler moveHandler = new MoveHandler(null, null, turnHandler);

		moveHandler.move("playerId2", null);
	}
}
