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
	private Othello othello;
	private GameExecutor gameExecutor;
	private String startingPlayerId;
	
	/**
	 * Creates a new tournament round.
	 * @param othello The game that will be played.
	 * @param gameExecutor The instance that will ensure that all players make moves until the game has ended.
	 * @param startingPlayerId The player that will begin.
	 */
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
