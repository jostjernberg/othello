package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

class SilentTournamentRound implements TournamentRound {
	Othello othello;
	String startingPlayerId;
	
	public SilentTournamentRound(Othello othello, String startingPlayerId) {
		this.othello = othello;
		this.startingPlayerId = startingPlayerId;
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

	@Override
	public String getStartingPlayerId() {
		return startingPlayerId;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(othello.getPlayers().get(0).getMoveStrategy().getName());
		sb.append(" vs. ");
		sb.append(othello.getPlayers().get(1).getMoveStrategy().getName());
		sb.append(" - starting strategy: ");
		sb.append(getPlayerWithId(startingPlayerId).getMoveStrategy().getName());
		
		return sb.toString();
	}
}
