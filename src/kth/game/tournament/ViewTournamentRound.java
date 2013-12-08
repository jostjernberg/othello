package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.view.swing.OthelloView;

class ViewTournamentRound implements TournamentRound{
	Othello othello;
	OthelloView othelloView;
	String startingPlayerId;
	
	public ViewTournamentRound(Othello othello, OthelloView othelloView, String startingPlayerId) {
		this.othello = othello;
		this.othelloView = othelloView;
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
		othelloView.start(startingPlayerId);
	}

	@Override
	public String getStartingPlayerId() {
		return startingPlayerId;
	}
}
