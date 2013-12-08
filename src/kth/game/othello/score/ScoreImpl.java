package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to monitor changes in the occupying player of all nodes in a board and calculate
 * the score of all players on this board according to a particular strategy.
 */
public class ScoreImpl extends Observable implements Score, Observer {
	Board board;
	ScoreCountingStrategy scoreCountingStrategy;
	List<Observer> observers;

	public ScoreImpl(Board board, ScoreCountingStrategy scoreCountingStrategy) {
		this.board = board;
		this.scoreCountingStrategy = scoreCountingStrategy;
		observers = new ArrayList<>();
		observe(board.getNodes());
	}
	
	private void observe(List<Node> nodes) {
		for(Node n : nodes) {
			n.addObserver(this);
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return scoreCountingStrategy.getPlayersScore(board);
	}

	@Override
	public int getPoints(String playerId) {
		return scoreCountingStrategy.getPoints(board, playerId);
	}

	@Override
	public void update(Observable o, Object arg) {
		Node node = (Node) o;
		String previousOccupyingPlayerId = (String) arg;

		List<String> notification = new ArrayList<String>();
		notification.add(node.getOccupantPlayerId());
		if (previousOccupyingPlayerId != null) {
			notification.add(previousOccupyingPlayerId);
		}

		for (Observer obs : observers) {
			obs.update(this, notification);
		}

	}
}
