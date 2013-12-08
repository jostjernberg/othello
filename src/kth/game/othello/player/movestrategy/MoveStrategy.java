package kth.game.othello.player.movestrategy;

import kth.game.othello.Rules;
import kth.game.othello.board.Board;
import kth.game.othello.board.Node;

/**
 * The responsibility of entities that implements this interface is to determine where a player will make its next move.
 */
public interface MoveStrategy {

	/**
	 * @return the name of the strategy
	 */
	public String getName();

	/**
	 * Determines which node the given player will make its next move at.
	 * 
	 * @param playerId the id of the player
	 * @param rules the rules of the game
	 * @param board the board
	 * @return the node where the player wants to move. If the player is not able to mave then null is returned.
	 */
	public Node move(String playerId, Rules rules, Board board);

}
