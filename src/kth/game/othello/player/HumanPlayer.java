package kth.game.othello.player;

/**
 * An immutable PlayerInstance with type Player.Type.HUMAN.
 * 
 * @author niclas
 * 
 */
public class HumanPlayer extends PlayerInstance {
	public HumanPlayer(String name) {
		super(name, Type.HUMAN);
	}
}