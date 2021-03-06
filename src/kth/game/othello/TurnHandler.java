package kth.game.othello;

import java.util.List;

import kth.game.othello.player.Player;

/**
 * The responsibility of this class is to manage which player is in turn.
 */
class TurnHandler {
	private Rules rules;
	private List<Player> players;
	private int nextPlayerIndex;

	public TurnHandler(Rules rules, List<Player> players) {
		this.rules = rules;
		this.players = players;
	}

	/**
	 * Returns the player who is next in turn. 
	 */
	public Player getPlayerInTurn() {
		return players.get(nextPlayerIndex);
	}

	/**
	 * Ends the turn of the current player in turn and passes it to the next player in turn.
	 */
	public void passTurnToNextPlayer() {
		do {
			nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
		} while (isActive() && !rules.hasValidMove(getPlayerInTurn().getId()));
	}

	/**
	 * Checks if the game is still active.
	 * 
	 * @return true if the rules allow any player to make a move, otherwise false;
	 */
	public boolean isActive() {
		for (Player p : players) {
			if (rules.hasValidMove(p.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Starts the game with the player that has id == playerId as the first player in turn.
	 */
	public void start(String playerId) {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getId().equals(playerId)) {
				nextPlayerIndex = i;
				return;
			}
		}
		// use default value if there are no players with id = playerId.
	}

	/**
	 * Starts the game with the first player in turn choosen randomly.
	 */
	public void start() {
		nextPlayerIndex = (int) (Math.random() * players.size());
	}
}
