package kth.game.othello.board;

/**
 * Placeholder class for a classical node.
 */
public class ClassicNode extends AbstractNode {

	public ClassicNode() {
		super();
	}

	@Override
	public String getOccupantPlayerId() {
		return null;
	}

	@Override
	public int getXCoordinate() {
		return 0;
	}

	@Override
	public int getYCoordinate() {
		return 0;
	}

	@Override
	public boolean isMarked() {
		return false;
	}
}
