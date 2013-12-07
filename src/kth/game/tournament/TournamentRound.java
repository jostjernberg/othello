package kth.game.tournament;

import java.util.List;
import java.util.Observer;

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
	
	/**
	 * The observers of this instance will be notified when the game has finished.
	 * @param obs
	 */
	public void addObserver(Observer obs);
}
