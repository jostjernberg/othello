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
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;
import kth.game.tournament.Tournament;
import kth.game.tournament.TournamentFactory;

public class Demo {
	private enum DemoNumber {
		DEMO_4, DEMO_5, DEMO_6, DEMO_7
	};

	private static final int TIME_BETWEEN_MOVES = 70; // ms
	private static final int TIME_BETWEEN_SWAPS = 10; // ms

	private DemoNumber demo = DemoNumber.DEMO_7;

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
		case DEMO_7:
			demo7();
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
				Thread.sleep(TIME_BETWEEN_MOVES);
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
		System.out.println("====== Demo start ======");
		System.out.println(othello);
		System.out.println();

		for (int round = 1; round <= 4 * othello.getPlayers().size(); round++) {

			String playerId = othello.getPlayerInTurn().getId();
			String nodeId = null;

			for (Node n : othello.getBoard().getNodes()) {
				if (othello.isMoveValid(playerId, n.getId())) {
					nodeId = n.getId();
				}
			}

			othello.move(playerId, nodeId);

			System.out.println("====== Round " + round + " ======");
			System.out.println(othello);
			System.out.println();

			try {
				Thread.sleep(TIME_BETWEEN_MOVES);
			} catch (InterruptedException ie) {
			}
		}

		System.out.println("====== Demo ended ======");
		System.out.println(othello);
	}

	private void createViewAndStartGame(Othello othello) {
		OthelloView othelloView = OthelloViewFactory.create(othello, TIME_BETWEEN_SWAPS, TIME_BETWEEN_MOVES);
		othelloView.start();
	}

	private void demo6() {
		PlayerCreator playerCreator = PlayerCreatorImpl.INSTANCE;
		List<Player> players = new ArrayList<>();
		players.add(playerCreator.createComputerPlayer("CPU"));
		players.add(playerCreator.createComputerPlayer("CPV"));
		players.add(playerCreator.createComputerPlayer("CPW"));

		BoardFactory boardFactory = new BoardFactory(NodeCreatorImpl.INSTANCE, BoardCreatorImpl.INSTANCE);
		Board board = boardFactory.getDiamondBoard(players, 9);

		OthelloFactory othelloFactory = new OthelloFactory(OthelloCreatorImpl.INSTANCE, null, null);
		Othello othello = othelloFactory.createGame(board, players);

		createViewAndStartGame(othello);

		// System.out.println(othello);

	}
	
	private void demo7() {
		OthelloCreator othelloCreator = OthelloCreatorImpl.INSTANCE;
		NodeCreator nodeCreator = NodeCreatorImpl.INSTANCE;
		BoardCreator boardCreator = BoardCreatorImpl.INSTANCE;
		PlayerCreator playerCreator = PlayerCreatorImpl.INSTANCE;
		BoardFactory boardFactory = new BoardFactory(nodeCreator, boardCreator);
		OthelloFactory othelloFactory = new OthelloFactory(othelloCreator, boardFactory, playerCreator);
		TournamentFactory tournamentFactory = new TournamentFactory(othelloFactory);
		
		Tournament tournament = tournamentFactory.createClassicTournament();
		System.out.println("Starting tournament!");
		tournament.start();
		System.out.println("Tournament ended.");
		
	}
}
