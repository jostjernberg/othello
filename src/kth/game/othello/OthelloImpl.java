package kth.game.othello;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import kth.game.othello.board.Board;
import kth.game.othello.board.BoardImpl;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

/**
 * The responsibility of this entity is to implement the Othello interface.
 */
class OthelloImpl extends Observable implements Othello {
	private Rules rules;
	private Board board;
	private TurnHandler turnHandler;
	private MoveHandler moveHandler;
	private Score score;
	private List<Player> players;
	private UUID id = UUID.randomUUID();
	private List<Observer> gameFinishedObservers = new ArrayList<>();
	private List<Observer> moveObservers = new ArrayList<>();

	/**
	 * Create a new game of othello with the injected dependenies. 
	 */
	public OthelloImpl(Board board, List<Player> players, Rules rules, MoveHandler moveHandler, TurnHandler turnHandler,
			Score score) {
		this.board = board;
		this.players = players;
		this.rules = rules;
		this.moveHandler = moveHandler;
		this.turnHandler = turnHandler;
		this.score = score;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public List<Node> getNodesToSwap(String playerId, String nodeId) {
		return rules.getNodesToSwap(playerId, nodeId);
	}

	@Override
	public Player getPlayerInTurn() {
		return turnHandler.getPlayerInTurn();
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Score getScore() {
		return this.score;
	}

	@Override
	public boolean hasValidMove(String playerId) {
		return rules.hasValidMove(playerId);
	}

	@Override
	public boolean isActive() {
		return turnHandler.isActive();
	}

	@Override
	public boolean isMoveValid(String playerId, String nodeId) {
		return rules.isMoveValid(playerId, nodeId);
	}

	@Override
	public List<Node> move() {
		if (!isActive()) {
			System.err.println("Attempting move after game over!");
			return null;
		}

		List<Node> swappedNodes = moveHandler.move();
		notifyMoveObserversAndGameFinishedObserversIfGameEnded(swappedNodes);
		return swappedNodes;
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		if (!isActive()) {
			System.err.println("Attempting move after game over!");
			return null;
		}

		List<Node> swappedNodes = moveHandler.move(playerId, nodeId);
		notifyMoveObserversAndGameFinishedObserversIfGameEnded(swappedNodes);
		return swappedNodes;
	}

	private void notifyMoveObserversAndGameFinishedObserversIfGameEnded(List<Node> swappedNodes) {
		if (swappedNodes.isEmpty()) {
			return;
		}

		for (Observer o : this.moveObservers) {
			o.update(this, swappedNodes);
		}
		if (!isActive()) {
			System.out.println("Game over!\n Last swap: ");
			for (Node n : swappedNodes) {
				System.out.print(" {" + n.getXCoordinate() + "," + n.getYCoordinate() + "}");
			}
			System.out.println();
			List<ScoreItem> score = this.score.getPlayersScore();

			for (Observer o : this.gameFinishedObservers) {
				o.update(this, score);
			}
		}
	}

	@Override
	public void start() {
		System.out.println("starting game!");
		turnHandler.start();
	}

	@Override
	public void start(String playerId) {
		System.out.println("starting game!");
		turnHandler.start(playerId);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--- Scores ---").append("\n");
		for (Player p : players) {
			if (board instanceof BoardImpl) {
				sb.append("(").append(((BoardImpl) board).playerIdToPrintableTag(p.getId())).append(") ");
			}
			sb.append(p.getName()).append(": ").append(getScore().getPoints(p.getId())).append("\n");
		}
		sb.append(board);
		return sb.toString();
	}

	@Override
	public void addGameFinishedObserver(Observer observer) {
		this.gameFinishedObservers.add(observer);
	}

	@Override
	public void addMoveObserver(Observer observer) {
		this.moveObservers.add(observer);
	}

	@Override
	public String getId() {
		return id.toString();
	}
}
