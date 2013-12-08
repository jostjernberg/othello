package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.view.swing.OthelloView;
import kth.game.tournament.executor.GameExecutor;

public class TournamentRound {
	Othello othello;
	GameExecutor gameExecutor;
	String startingPlayerId;
	
	public TournamentRound(Othello othello, GameExecutor gameExecutor, String startingPlayerId) {
		this.othello = othello;
		this.gameExecutor = gameExecutor;
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
	
	public void start() {
		gameExecutor.start(startingPlayerId);
	}

	public String getStartingPlayerId() {
		return startingPlayerId;
	}
}
