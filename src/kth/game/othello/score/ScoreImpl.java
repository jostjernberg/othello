package kth.game.othello.score;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;

class ScoreImpl implements Score, Observer {
	Board board;
	List<Player> players;

	/**
	 * 
	 * @throws IllegalArgumentException
	 *             if there is no player with playerId.
	 */
	public void setScore(String playerId, int score) throws IllegalArgumentException {
		// TODO
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ScoreItem> getPlayersScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPoints(String playerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setScoreCountingStrategy(ScoreCountingStrategy strategy) {
		// TODO
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
