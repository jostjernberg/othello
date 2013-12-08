package kth.game.othello;

import java.util.List;

import kth.game.othello.board.Board;
import kth.game.othello.player.Player;
import kth.game.othello.score.ClassicScoreCountingStrategy;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreImpl;

/**
 * The responsibility of this entity is to implement the OthelloCreator interface.
 */
class OthelloCreatorImpl implements OthelloCreator {
	public static OthelloCreatorImpl INSTANCE = new OthelloCreatorImpl();

	private OthelloCreatorImpl() {
		// empty
	}

	@Override
	public Othello createOthello(Board board, List<Player> players) {
		Rules rules = new ClassicRules(board);
		TurnHandler turnHandler = new TurnHandler(rules, players);
		MoveHandler moveHandler = new MoveHandler(board, rules, turnHandler);
		Score score = new ScoreImpl(board, ClassicScoreCountingStrategy.INSTANCE);

		return new OthelloImpl(board, players, rules, moveHandler, turnHandler, score);
	}
}
