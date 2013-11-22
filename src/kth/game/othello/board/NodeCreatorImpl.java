package kth.game.othello.board;

public class NodeCreatorImpl implements NodeCreator {
	public static NodeCreatorImpl INSTANCE = new NodeCreatorImpl();

	private NodeCreatorImpl() {

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
