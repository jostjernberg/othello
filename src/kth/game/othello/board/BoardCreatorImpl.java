package kth.game.othello.board;

import java.util.List;

public class BoardCreatorImpl implements BoardCreator {
	public static BoardCreatorImpl INSTANCE = new BoardCreatorImpl();

	private BoardCreatorImpl() {

	}

	@Override
	public Board createBoard(List<Node> nodes) {
		return new BoardImpl(nodes);
	}

}
