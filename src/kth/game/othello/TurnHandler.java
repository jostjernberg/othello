package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to manage which player is in turn.
 */
public class TurnHandler {
	MoveHandler moveHandler;
	List<Player> players;

	public TurnHandler(MoveHandler moveHandler, List<Player> players) {
		this.moveHandler = moveHandler;
		this.players = players;
	}

	public Player getPlayerInTurn() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Ends the turn of the current player in turn and passes it to the next player in turn.
	 */
	public void passTurnToNextPlayer() {
		// TODO
	}

	/**
	 * Checks if the game is still active.
	 * 
	 * @return true if the rules allow any player to make a move, otherwise false;
	 */
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	public void start(String playerId) {
		// TODO Auto-generated method stub

	}

	public void start() {
		// TODO Auto-generated method stub

	}

}
