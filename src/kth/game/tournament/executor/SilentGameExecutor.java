package kth.game.tournament.executor;

import kth.game.othello.Othello;

public class SilentGameExecutor implements GameExecutor {
	Othello othello;
	
	public SilentGameExecutor(Othello othello) {
		this.othello = othello;
	}
	
	@Override
	public void start(String playerId) {
		othello.start(playerId);
		moveUntilGameHasFinished();
	}
	
	private void moveUntilGameHasFinished() {
		while(othello.isActive()) {
			othello.move();
		}
	}

}
