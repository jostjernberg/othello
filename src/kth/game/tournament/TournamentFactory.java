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
import kth.game.tournament.result.SimpleResultPresenter;

public class TournamentFactory {
	OthelloFactory othelloFactory;
	
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
				TournamentRound roundOne = new SilentTournamentRound(gameOne, gameOneStartingPlayerId);
				TournamentRound roundTwo = new SilentTournamentRound(gameTwo, gameTwoStartingPlayerId);				
				
				tournamentRounds.add(roundOne);
				tournamentRounds.add(roundTwo);
			}
		}
		
		return new Tournament(tournamentRounds, SimpleResultPresenter.INSTANCE);
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
				TournamentRound roundOne = new ViewTournamentRound(gameOne, othelloViewOne, gameOneStartingPlayerId);
				TournamentRound roundTwo = new ViewTournamentRound(gameTwo, othelloViewTwo, gameTwoStartingPlayerId);				
				
				tournamentRounds.add(roundOne);
				tournamentRounds.add(roundTwo);
			}
		}
		
		return new Tournament(tournamentRounds, SimpleResultPresenter.INSTANCE);
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
