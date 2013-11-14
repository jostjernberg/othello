package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Node;
import kth.game.othello.player.Player;

public class Demo {
	private enum DemoNumber {
		DEMO_1, DEMO_2
	};

	private DemoNumber demo = DemoNumber.DEMO_2;

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

	private void demo1() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		othello.start();
		while (othello.isActive()) {
			List<Node> nodesToSwap = othello.move();
			if (nodesToSwap.size() == 0) {
				throw new IllegalStateException("Can't make a move.");
			}
			System.out.println(othello);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
		}
	}

	private void demo2() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		othello.start();
		while (othello.isActive()) {
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
		}
	}
}
