package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class OthelloImpl implements Othello {
	private Rules rules;
	private Board board;
	private TurnHandler turnHandler;
	private MoveHandler moveHandler;
	private Score score;
	private List<Player> players;

	OthelloImpl(Board board, List<Player> players, Rules rules, MoveHandler moveHandler, TurnHandler turnHandler,
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
		return moveHandler.move();
	}

	@Override
	public List<Node> move(String playerId, String nodeId) throws IllegalArgumentException {
		return moveHandler.move(playerId, nodeId);
	}

	@Override
	public void start() {
		turnHandler.start();
	}

	@Override
	public void start(String playerId) {
		turnHandler.start(playerId);
	}
}