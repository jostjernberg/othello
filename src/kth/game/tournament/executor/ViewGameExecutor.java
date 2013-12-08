package kth.game.tournament.executor;

import kth.game.othello.Othello;
import kth.game.othello.view.swing.OthelloView;

public class ViewGameExecutor implements GameExecutor{
	OthelloView othelloView;
	
	public ViewGameExecutor(OthelloView othelloView) {
		this.othelloView = othelloView;
	}
	
	@Override
	public void start(String playerId) {
		othelloView.start(playerId);
	}
}
