package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.InternalBoard;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

public class ClassicOthelloFactory implements OthelloFactory {
	private static int CLASSIC_DIMENSION_HEIGHT = 8;
	private static int CLASSIC_DIMENSION_WIDTH = 8;

	public ClassicOthelloFactory() {

	}

	/**
	 * Creates the standard 8x8 othello board with initial player placements.
	 * 
	 * @param p1
	 *            Player one.
	 * @param p2
	 *            Player two.
	 * @return A standard 8x8 othello board with initial player placements.
	 */
	private InternalBoard defaultOthelloBoard(Player p1, Player p2) {
		InternalBoard board = new ClassicBoard(CLASSIC_DIMENSION_HEIGHT, CLASSIC_DIMENSION_WIDTH);
		board.changeOccupant(3, 4, p1.getId());
		board.changeOccupant(4, 3, p1.getId());
		board.changeOccupant(3, 3, p2.getId());
		board.changeOccupant(4, 4, p2.getId());
		return board;
	}

	@Override
	public Othello createComputerGameOnClassicalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new ComputerPlayer("CPU1"));
		players.add(new ComputerPlayer("CPU2"));

		return new ClassicOthello(players, defaultOthelloBoard(players.get(0), players.get(1)),
				CLASSIC_DIMENSION_HEIGHT, CLASSIC_DIMENSION_WIDTH);
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer("PLR1"));
		players.add(new HumanPlayer("PLR2"));

		return new ClassicOthello(players, defaultOthelloBoard(players.get(0), players.get(1)),
				CLASSIC_DIMENSION_HEIGHT, CLASSIC_DIMENSION_WIDTH);
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer("PLR1"));
		players.add(new ComputerPlayer("CPU1"));

		return new ClassicOthello(players, defaultOthelloBoard(players.get(0), players.get(1)),
				CLASSIC_DIMENSION_HEIGHT, CLASSIC_DIMENSION_WIDTH);
	}
}
