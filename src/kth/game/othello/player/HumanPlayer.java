package kth.game.othello.player;

/**
 * An immutable PlayerInstance with type Player.Type.HUMAN.
 */
public class HumanPlayer extends AbstractPlayer {
	public HumanPlayer(String name) {
		super(name, Type.HUMAN);
	}
}