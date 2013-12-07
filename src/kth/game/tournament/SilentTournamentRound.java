package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class SilentTournamentRound implements TournamentRound {
	Othello othello;
	
	public SilentTournamentRound(Othello othello) {
		this.othello = othello;
	}
	
	public Score getScore() {
		return othello.getScore();
	}
	
	public List<Player> getPlayers() {
		return othello.getPlayers();
	}
	
	public Player getPlayerWithId(String playerId) {
		Player player = null;
		
		for(Player p : othello.getPlayers()) {
			if(p.getId().equals(playerId)) {
				player = p;
			}
		}
		
		return player;
	}
	
	@Override
	public void start() {
		othello.start();
		moveUntilGameHasFinished();
	}
	
	private void moveUntilGameHasFinished() {
		while(othello.isActive()) {
			othello.move();
		}
	}
}
