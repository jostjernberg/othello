package kth.game.othello;

import kth.game.othello.board.BoardCreatorImpl;
import kth.game.othello.board.NodeCreatorImpl;
import kth.game.othello.board.factory.BoardFactory;
import kth.game.othello.player.PlayerCreatorImpl;
import kth.game.tournament.Tournament;
import kth.game.tournament.TournamentFactory;

import org.junit.Test;

public class TournamentIT {

	@Test
	public void canPerformTournamentWithoutView() {
		BoardFactory boardFactory = new BoardFactory(NodeCreatorImpl.INSTANCE, BoardCreatorImpl.INSTANCE);
		OthelloFactory othelloFactory = new OthelloFactory(OthelloCreatorImpl.INSTANCE, boardFactory, PlayerCreatorImpl.INSTANCE);
		TournamentFactory tournamentFactory = new TournamentFactory(othelloFactory);
		Tournament tournament = tournamentFactory.createClassicTournamentWithoutView();
		tournament.start();
	}

}
