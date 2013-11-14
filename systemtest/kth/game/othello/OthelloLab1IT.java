package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.player.Player.Type;

import org.junit.Assert;
import org.junit.Test;

public class OthelloLab1IT {

	private Object getNumberOfOccupiedNodes(Othello othello) {
		int occupiedNodesCounter = 0;
		for (Node node : othello.getBoard().getNodes()) {
			if (node.isMarked()) {
				occupiedNodesCounter++;
			}
		}
		return occupiedNodesCounter;
	}

	private OthelloFactory getOthelloFactory() {
		return new ClassicOthelloFactory();
	}

	private void makeAHumanMove(Othello othello, Player human) {
		for (Node node : othello.getBoard().getNodes()) {
			if (othello.isMoveValid(human.getId(), node.getId())) {
				othello.move(human.getId(), node.getId());
				return;
			}
		}
		throw new IllegalStateException(othello.toString());
	}

	@Test
	public void someMovesBetweenAComputerAndHumanTest() {
		Othello othello = getOthelloFactory().createHumanVersusComputerGameOnOriginalBoard();
		Player human = null;
		if (othello.getPlayers().get(0).getType() == Type.COMPUTER) {
			human = othello.getPlayers().get(1);
		} else {
			human = othello.getPlayers().get(0);
		}
		othello.start(human.getId());
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);
		othello.move();
		makeAHumanMove(othello, human);
		othello.move();
		Assert.assertEquals(10, getNumberOfOccupiedNodes(othello));
	}

	@Test
	public void makeOneMoveTest() {
		Othello othello = getOthelloFactory().createHumanGameOnOriginalBoard();
		Node lowerLeftPlacedNode = getNode(othello, 3, 3);
		String playerId = lowerLeftPlacedNode.getOccupantPlayerId();
		othello.start(playerId);

		Node nextPlacement = getNode(othello, 5, 3);

		List<Node> nodesToSwap = othello.move(playerId, nextPlacement.getId());

		Assert.assertEquals(nodesToSwap.size(), 2);
		Assert.assertEquals(nodesToSwap.get(0).getXCoordinate(), 4);
		Assert.assertEquals(nodesToSwap.get(0).getYCoordinate(), 3);

		Assert.assertEquals(nodesToSwap.get(1).getXCoordinate(), 5);
		Assert.assertEquals(nodesToSwap.get(1).getYCoordinate(), 3);

		Assert.assertEquals(getNode(othello, 5, 3).getOccupantPlayerId(), playerId);
		Assert.assertEquals(getNode(othello, 4, 3).getOccupantPlayerId(), playerId);
	}

	private Node getNode(Board board, int x, int y) {
		for (Node n : board.getNodes()) {
			if (n.getXCoordinate() == x && n.getYCoordinate() == y) {
				return n;
			}
		}
		throw new IllegalArgumentException("Can't find node with coordinates (" + x + ", " + y + ").");
	}

	private Node getNode(Othello othello, int x, int y) {
		return getNode(othello.getBoard(), x, y);
	}

	@Test
	public void twoComputerOnAClassicalBoardTest() {
		Othello othello = getOthelloFactory().createComputerGameOnClassicalBoard();
		othello.start(othello.getPlayers().get(0).getId());
		while (othello.isActive()) {
			Assert.assertEquals(Type.COMPUTER, othello.getPlayerInTurn().getType());
			othello.move();
		}
	}

}
