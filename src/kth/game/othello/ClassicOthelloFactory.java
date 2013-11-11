package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.ClassicBoard;
import kth.game.othello.board.ClassicNode;
import kth.game.othello.board.Node;
import kth.game.othello.player.ComputerPlayer;
import kth.game.othello.player.HumanPlayer;
import kth.game.othello.player.Player;

public class ClassicOthelloFactory implements OthelloFactory {

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
	private Board defaultOthelloBoard(Player p1, Player p2) {
		List<Node> nodes = new ArrayList<Node>();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (c == 3 && r == 4 || c == 4 && r == 3) {
					nodes.add(new ClassicNode(r, c, p1));
				} else if (c == 3 && r == 3 || c == 4 && r == 4) {
					nodes.add(new ClassicNode(c, r, p2));
				} else {
					nodes.add(new ClassicNode(c, r));
				}
			}
		}

		return new ClassicBoard(nodes);
	}

	@Override
	public Othello createComputerGameOnClassicalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new ComputerPlayer("CPU1"));
		players.add(new ComputerPlayer("CPU2"));

		return new ClassicOthello(defaultOthelloBoard(players.get(0), players.get(1)), players);
	}

	@Override
	public Othello createHumanGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer("PLR1"));
		players.add(new HumanPlayer("PLR2"));

		return new ClassicOthello(defaultOthelloBoard(players.get(0), players.get(1)), players);
	}

	@Override
	public Othello createHumanVersusComputerGameOnOriginalBoard() {
		List<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer("PLR1"));
		players.add(new ComputerPlayer("CPU1"));

		return new ClassicOthello(defaultOthelloBoard(players.get(0), players.get(1)), players);
	}
}
