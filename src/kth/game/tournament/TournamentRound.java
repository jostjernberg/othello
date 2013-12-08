package kth.game.tournament;

import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.tournament.executor.GameExecutor;

/**
 * The responsibility of this entity is to hold an othello game and a way to execute it, to be used in a tournament.
 */
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
	
	/**
	 * Returns the player in this tournament round with the given playerId.
	 * @return null if player isn't present.
	 */
	public Player getPlayerWithId(String playerId) {
		Player player = null;
		
		for(Player p : othello.getPlayers()) {
			if(p.getId().equals(playerId)) {
				player = p;
			}
		}
		
		return player;
	}

	/**
	 * Start this tournament round.
	 */
	public void start() {
		gameExecutor.start(startingPlayerId);
	}

	public String getStartingPlayerId() {
		return startingPlayerId;
	}
}
