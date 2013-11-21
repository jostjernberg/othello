package kth.game.othello.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * The responsibility of this class is to monitor changes in the occupying player of all nodes in a board and calculate
 * the score of all players on this board given according to a particular strategy.
 */
class ScoreImpl extends Observable implements Score, Observer {
	Board board;
	ScoreCountingStrategy scoreCountingStrategy;
	List<Observer> observers;

	ScoreImpl(Board board, ScoreCountingStrategy scoreCountingStrategy) {
		this.board = board;
		this.scoreCountingStrategy = scoreCountingStrategy;
		observers = new ArrayList<>();
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		return scoreCountingStrategy.getPlayersScore();
	}

	@Override
	public int getPoints(String playerId) {
		return scoreCountingStrategy.getPoints(playerId);
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
