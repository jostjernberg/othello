package kth.game.othello.player;

/**
 * An immutable player representation with type Player.Type.COMPUTER.
 */
public class ComputerPlayer extends AbstractPlayer {
	public ComputerPlayer(String name) {
		super(name, Type.COMPUTER);
	}
}