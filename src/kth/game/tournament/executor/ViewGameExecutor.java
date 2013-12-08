package kth.game.tournament.executor;

import kth.game.othello.view.swing.OthelloView;

/**
 * The responsibility of this class is to make sure that a game is played from start to end, using an OthelloView.
 */
public class ViewGameExecutor implements GameExecutor{
	private OthelloView othelloView;
	
	public ViewGameExecutor(OthelloView othelloView) {
		this.othelloView = othelloView;
	}
	
	@Override
	public void start(String playerId) {
		othelloView.start(playerId);
	}
}
