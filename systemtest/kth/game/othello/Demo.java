package kth.game.othello;

import kth.game.othello.board.InternalBoard;
import kth.game.othello.player.Player;

public class Demo {
	private enum DemoNumber {
		DEMO_1, DEMO_2
	};

	private static final int MOVE_PAUSE = 100; // ms between each move

	private DemoNumber demo = DemoNumber.DEMO_1;

	public static void main(String args[]) {
		new Demo();
	}

	private Demo() {
		switch (demo) {
		case DEMO_1:
			demo1();
			break;
		case DEMO_2:
			demo2();
			break;

		}
	}

	// TODO: move this to actual game
	private void printWinner(Othello othello) {
		InternalBoard board = (InternalBoard) othello.getBoard();
		String winner = board.getLeadingPlayerId();
		if (winner == InternalBoard.RESULT_TIE) {
			System.out.println("The game is tied!");
		} else {
			System.out.println("The winner is player " + winner);
		}
		System.out.println("Please come again.");
	}

	private void demo1() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		othello.start();
		System.out.println(othello);
		while (othello.isActive()) {
			try {
				Thread.sleep(MOVE_PAUSE);
			} catch (InterruptedException ie) {

			}
			othello.move();
			System.out.println(othello);
		}
		System.out.println(othello);
		System.out.println("Game ended!");
		printWinner(othello);
	}

	private void demo2() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		othello.start();
		System.out.println(othello);
		while (othello.isActive()) {
			try {
				Thread.sleep(MOVE_PAUSE);
			} catch (InterruptedException ie) {

			}
			if (othello.getPlayerInTurn().getType() == Player.Type.HUMAN) {
				for (int i = othello.getBoard().getNodes().size() - 1; i >= 0; i--) {
					if (othello.isMoveValid(othello.getPlayerInTurn().getId(), othello.getBoard().getNodes().get(i)
							.getId())) {
						othello.move(othello.getPlayerInTurn().getId(), othello.getBoard().getNodes().get(i).getId());
					}
				}
			} else {
				othello.move();
			}
			System.out.println(othello);
		}
		System.out.println(othello);
		System.out.println("Game ended.");
		printWinner(othello);
	}
}
