package kth.game.othello;

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
		System.out.println(othello);
		while (othello.isActive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
			othello.move();
			System.out.println(othello);
		}
		System.out.println(othello);
		System.out.println("Game ended!");
	}

	private void demo2() {
		OthelloFactory othelloFactory = new ClassicOthelloFactory();
		Othello othello = othelloFactory.createHumanVersusComputerGameOnOriginalBoard();
		othello.start();
		System.out.println(othello);
		while (othello.isActive()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {

			}
			if (othello.getPlayerInTurn().getType() == Player.Type.HUMAN) {
				for (int i = othello.getBoard().getNodes().size() - 1; i >= 0; i--) {
					if (othello.isMoveValid(othello.getPlayerInTurn().getId(), othello.getBoard().getNodes().get(i)
							.getId())) {
						othello.move(othello.getPlayerInTurn().getId(), othello.getBoard().getNodes().get(i).getId());
						break;
					}
				}
			} else {
				othello.move();
			}
			System.out.println(othello);
		}
		System.out.println(othello);
		System.out.println("Game ended!");
	}
}
