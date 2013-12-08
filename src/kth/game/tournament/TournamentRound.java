package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.Player;

public interface TournamentRound {
	
	/**
	 * Returns a list of all players in this round.
	 */
	public List<Player> getPlayers();
	
	/**
	 * Return the id of the player that will start in this round.
	 * @return
	 */
	public String getStartingPlayerId();

	/**
	 * Returns the player in this tournament round with the given playerId.
	 * @return null if player isn't present.
	 */
	public Player getPlayerWithId(String playerId);
	
	/**
	 * Returns the score of the player in this round with id = playerId.
	 * @return 0 if player isn't present.
	 */
	public int getPoints(String playerId);

	/**
	 * Start this tournament round.
	 */
	public void start();

	
}
