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
import kth.game.tournament.executor.GameExecutor;
import kth.game.tournament.executor.SilentGameExecutor;
import kth.game.tournament.executor.ViewGameExecutor;
import kth.game.tournament.result.ResultPresenter;
import kth.game.tournament.result.SimpleResultPresenter;
import kth.game.tournament.result.SimpleResultRanker;

public class TournamentFactory {
	private OthelloFactory othelloFactory;
	
	public TournamentFactory(OthelloFactory othelloFactory) {
		this.othelloFactory = othelloFactory;
	}
	
	public Tournament createClassicTournamentWithoutView() {
		List<MoveStrategy> strategies = getStrategies();
		
		List<TournamentRound> tournamentRounds = new ArrayList<>();
		for(MoveStrategy playerOneStrategy : strategies) {
			for(MoveStrategy playerTwoStrategy : strategies) {
				if(playerOneStrategy.equals(playerTwoStrategy)) {
					continue;
				}
				Othello gameOne = createMatchup(playerOneStrategy, playerTwoStrategy);
				Othello	gameTwo = createMatchup(playerOneStrategy, playerTwoStrategy);
				String gameOneStartingPlayerId = gameOne.getPlayers().get(0).getId();
				String gameTwoStartingPlayerId = gameTwo.getPlayers().get(1).getId();
				GameExecutor gameExecutorOne = new SilentGameExecutor(gameOne);
				GameExecutor gameExecutorTwo = new SilentGameExecutor(gameTwo);
				TournamentRound roundOne = new TournamentRoundImpl(gameOne, gameExecutorOne, gameOneStartingPlayerId);
				TournamentRound roundTwo = new TournamentRoundImpl(gameTwo, gameExecutorTwo, gameTwoStartingPlayerId);				
				
				tournamentRounds.add(roundOne);
				tournamentRounds.add(roundTwo);
			}
		}
		
		ResultPresenter resultPresenter = new SimpleResultPresenter(SimpleResultRanker.INSTANCE);
		return new TournamentImpl(tournamentRounds, resultPresenter);
	}
	
	public Tournament createClassicTournamentWithView(int timeBetweenSwaps, int timeBetweenMoves) {
		List<MoveStrategy> strategies = getStrategies();
		
		List<TournamentRound> tournamentRounds = new ArrayList<>();
		for(MoveStrategy playerOneStrategy : strategies) {
			for(MoveStrategy playerTwoStrategy : strategies) {
				if(playerOneStrategy.equals(playerTwoStrategy)) {
					continue;
				}
				Othello gameOne = createMatchup(playerOneStrategy, playerTwoStrategy);
				Othello	gameTwo = createMatchup(playerOneStrategy, playerTwoStrategy);
				OthelloView othelloViewOne = OthelloViewFactory.create(gameOne, timeBetweenSwaps, timeBetweenMoves);
				OthelloView othelloViewTwo = OthelloViewFactory.create(gameTwo, timeBetweenSwaps, timeBetweenMoves);
				String gameOneStartingPlayerId = gameOne.getPlayers().get(0).getId();
				String gameTwoStartingPlayerId = gameTwo.getPlayers().get(1).getId();
				GameExecutor gameExecutorOne = new ViewGameExecutor(othelloViewOne);
				GameExecutor gameExecutorTwo = new ViewGameExecutor(othelloViewTwo);
				
				TournamentRound roundOne = new TournamentRoundImpl(gameOne, gameExecutorOne, gameOneStartingPlayerId);
				TournamentRound roundTwo = new TournamentRoundImpl(gameTwo, gameExecutorTwo, gameTwoStartingPlayerId);				
				
				tournamentRounds.add(roundOne);
				tournamentRounds.add(roundTwo);
			}
		}
		
		ResultPresenter resultPresenter = new SimpleResultPresenter(SimpleResultRanker.INSTANCE);
		return new TournamentImpl(tournamentRounds, resultPresenter);
	}
	
	private List<MoveStrategy> getStrategies() {
		List<MoveStrategy> strategies = new ArrayList<>();
		strategies.add(RandomMoveStrategy.INSTANCE);
		strategies.add(GreedyMoveStrategy.INSTANCE);
		strategies.add(ImpatientMoveStrategy.INSTANCE);
		return strategies;
	}
	
	private Othello createMatchup(MoveStrategy playerOneStrategy, MoveStrategy playerTwoStrategy) {
		Othello othello = othelloFactory.createComputerGameOnClassicalBoard();
		othello.getPlayers().get(0).setMoveStrategy(playerOneStrategy);
		othello.getPlayers().get(1).setMoveStrategy(playerTwoStrategy);
		return othello;
	}
}
