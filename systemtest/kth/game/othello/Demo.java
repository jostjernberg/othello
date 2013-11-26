package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardCreator;
import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.Node;
import kth.game.othello.board.NodeCreator;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.Player;
import kth.game.othello.player.PlayerCreator;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;

public class Demo {
	private enum DemoNumber {
		DEMO_4, DEMO_5, DEMO_6
	};

	private static final int MOVE_PAUSE = 0; // ms between each move

	private DemoNumber demo = DemoNumber.DEMO_6;

	public static void main(String args[]) {
		new Demo();
	}

	private Demo() {
		switch (demo) {
		case DEMO_4:
			demo4();
			break;
		case DEMO_5:
			demo5();
			break;
		case DEMO_6:
			demo6();
			break;
		}
	}

	private void demo4() {
		OthelloCreator othelloCreator = OthelloCreatorImpl.INSTANCE;
		NodeCreator nodeCreator = NodeCreatorImpl.INSTANCE;
		BoardCreator boardCreator = BoardCreatorImpl.INSTANCE;
		PlayerCreator playerCreator = PlayerCreatorImpl.INSTANCE;
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		OthelloFactory othelloFactory = new OthelloFactory(othelloCreator, boardFactory, playerCreator);
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();

		int moveStrategyIndex = 0;
		List<MoveStrategy> moveStrategies = new ArrayList<>();
		moveStrategies.add(GreedyMoveStrategy.INSTANCE);
		moveStrategies.add(RandomMoveStrategy.INSTANCE);

		othello.start();

		int round = 1;
		while (othello.isActive()) {
			System.out.println("====== Round " + round + " ======");
			System.out.println(othello);
			System.out.println();
			othello.move();
			round++;

			if (round % 10 == 0) {
				othello.getPlayers().get(0).setMoveStrategy(moveStrategies.get(moveStrategyIndex));
				moveStrategyIndex = (moveStrategyIndex + 1) % moveStrategies.size();
			}

			try {
				Thread.sleep(MOVE_PAUSE);
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("====== Round " + round + " ======");
		System.out.println(othello);
	}

	private void demo5() {
		OthelloCreator othelloCreator = OthelloCreatorImpl.INSTANCE;
		NodeCreator nodeCreator = NodeCreatorImpl.INSTANCE;
		BoardCreator boardCreator = BoardCreatorImpl.INSTANCE;
		PlayerCreator playerCreator = PlayerCreatorImpl.INSTANCE;
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		OthelloFactory othelloFactory = new OthelloFactory(othelloCreator, boardFactory, playerCreator);
		Othello othello = othelloFactory.createHumanGameOnOriginalBoard();

		othello.start();

		int round = 1;
		while (othello.isActive()) {
			System.out.println("====== Round " + round + " ======");
			System.out.println(othello);
			System.out.println();

			String playerId = othello.getPlayerInTurn().getId();
			String nodeId = null;

			for (Node n : othello.getBoard().getNodes()) {
				if (othello.isMoveValid(playerId, n.getId())) {
					nodeId = n.getId();
				}
			}

			othello.move(playerId, nodeId);
			round++;

			try {
				Thread.sleep(MOVE_PAUSE);
			} catch (InterruptedException ie) {
			}
		}

		System.out.println("====== Round " + round + " ======");
		System.out.println(othello);
	}

	private void demo6() {
		PlayerCreator playerCreator = PlayerCreatorImpl.INSTANCE;
		List<Player> players = new ArrayList<>();
		players.add(playerCreator.createComputerPlayer("CPU1"));
		players.add(playerCreator.createComputerPlayer("CPU2"));
		players.add(playerCreator.createComputerPlayer("CPU3"));

		BoardFactory boardFactory = new BoardFactory(NodeCreatorImpl.INSTANCE, BoardCreatorImpl.INSTANCE);
		Board board = boardFactory.getDiamondBoard(players, 9);

		OthelloFactory othelloFactory = new OthelloFactory(OthelloCreatorImpl.INSTANCE, null, null);
		Othello othello = othelloFactory.createGame(board, players);

		othello.start();

		int round = 1;
		while (othello.isActive()) {
			System.out.println("====== Round " + round + " ======");
			System.out.println(othello);
			System.out.println();

			othello.move();
			round++;

			try {
				Thread.sleep(MOVE_PAUSE);
			} catch (InterruptedException ie) {
			}
		}
		System.out.println("====== Round " + round + " ======");
		System.out.println(othello);
	}
}
