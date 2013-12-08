package kth.game.tournament.executor;

import kth.game.othello.Othello;

/**
 * The responsibility of this class is to take a Computer vs. Computer othello game and force the players to make moves until the 
 * game is over.
 */
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
