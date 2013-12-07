package kth.game.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.OthelloFactory;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.ImpatientMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.othello.view.swing.OthelloView;
import kth.game.othello.view.swing.OthelloViewFactory;

public class TournamentFactory {
	OthelloFactory othelloFactory;
	
	public TournamentFactory(OthelloFactory othelloFactory) {
		this.othelloFactory = othelloFactory;
	}
	
	public Tournament createClassicTournament() {
		List<TournamentRound> tournamentRounds = createSilentTournamentRoundsBetweenComputersOnClassicBoard();
		return new Tournament(tournamentRounds, SimpleResultPresenter.INSTANCE);
	}
	
	public Tournament createClassicTournamentWithView(int timeBetweenSwaps, int timeBetweenMoves) {
		List<TournamentRound> tournamentRounds = createViewTournamentRoundsBetweenComputersOnClassicBoard(timeBetweenSwaps, timeBetweenMoves);
		return new Tournament(tournamentRounds, SimpleResultPresenter.INSTANCE);
	}
	
	private List<TournamentRound> createViewTournamentRoundsBetweenComputersOnClassicBoard(int timeBetweenSwaps, int timeBetweenMoves) {
		List<MoveStrategy> strategies = new ArrayList<>();
		strategies.add(RandomMoveStrategy.INSTANCE);
		strategies.add(GreedyMoveStrategy.INSTANCE);
		strategies.add(ImpatientMoveStrategy.INSTANCE);
		
		List<TournamentRound> tournamentRounds = new ArrayList<>();
		for(MoveStrategy playerOneStrategy : strategies) {
			for(MoveStrategy playerTwoStrategy : strategies) {
				tournamentRounds.add(createViewTournamentRound(playerOneStrategy, playerTwoStrategy, timeBetweenSwaps, timeBetweenMoves));
			}
		}
		
		return tournamentRounds;
	}

	private TournamentRound createViewTournamentRound(
			MoveStrategy playerOneStrategy, MoveStrategy playerTwoStrategy, int timeBetweenSwaps, int timeBetweenMoves) {
		Othello othello = createMatchup(playerOneStrategy, playerTwoStrategy);
		OthelloView othelloView = OthelloViewFactory.create(othello, timeBetweenSwaps, timeBetweenMoves);
		return new ViewTournamentRound(othello, othelloView);
	}

	private List<TournamentRound> createSilentTournamentRoundsBetweenComputersOnClassicBoard() {
		List<MoveStrategy> strategies = new ArrayList<>();
		strategies.add(RandomMoveStrategy.INSTANCE);
		strategies.add(GreedyMoveStrategy.INSTANCE);
		strategies.add(ImpatientMoveStrategy.INSTANCE);
		
		List<TournamentRound> tournamentRounds = new ArrayList<>();
		for(MoveStrategy playerOneStrategy : strategies) {
			for(MoveStrategy playerTwoStrategy : strategies) {
				tournamentRounds.add(createSilentTournamentRound(playerOneStrategy, playerTwoStrategy));
			}
		}
		
		return tournamentRounds;
	}
	
	private SilentTournamentRound createSilentTournamentRound(MoveStrategy playerOneStrategy, MoveStrategy playerTwoStrategy) {
		Othello othello = createMatchup(playerOneStrategy, playerTwoStrategy);
		return new SilentTournamentRound(othello);
	}
	
	private Othello createMatchup(MoveStrategy playerOneStrategy, MoveStrategy playerTwoStrategy) {
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		othello.getPlayers().get(0).setMoveStrategy(playerOneStrategy);
		othello.getPlayers().get(1).setMoveStrategy(playerTwoStrategy);
		return othello;
	}
}
