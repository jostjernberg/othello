package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.score.Score;

public interface TournamentRound {
	
	/**
	 * Start this game round.
	 */
	public void start();
	
	public Score getScore();
	
	public List<Player> getPlayers();
	
	public Player getPlayerWithId(String playerId);
}
