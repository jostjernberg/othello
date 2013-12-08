package kth.game.othello.board;

/**
 *	The responsibility of this entity is to implement the NodeCreator interface. 
 */
public class NodeCreatorImpl implements NodeCreator {
	public static NodeCreatorImpl INSTANCE = new NodeCreatorImpl();

	private NodeCreatorImpl() {
		// empty
	}

	@Override
	public Node createNodeWithCoordinate(int x, int y) {
		return new NodeImpl(x, y);
	}

	@Override
	public Node createNodeWithCoordinateAndOccupant(int x, int y, String occupantPlayerId) {
		return new NodeImpl(x, y, occupantPlayerId);
	}

}
