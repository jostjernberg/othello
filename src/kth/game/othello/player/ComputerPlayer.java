package kth.game.othello.player;

/**
 * An immutable PlayerInstance with type Player.Type.COMPUTER.
 * 
 * @author niclas
 * 
 */
public class ComputerPlayer extends PlayerInstance {
	public ComputerPlayer(String name) {
		super(name, Type.COMPUTER);
	}
}