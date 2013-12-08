package kth.game.tournament.result;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;
import kth.game.othello.player.movestrategy.GreedyMoveStrategy;
import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.player.movestrategy.RandomMoveStrategy;
import kth.game.tournament.Tournament;
import kth.game.tournament.TournamentRound;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SimpleResultRankerTest {

	@Test
	public void test() {
		// --- setup ---
		List<TournamentRound> rounds = new ArrayList<>();
		for(int i = 0; i < 4; i++) {
			Player playerOne = Mockito.mock(Player.class);
			Player playerTwo = Mockito.mock(Player.class);
			Mockito.when(playerOne.getId()).thenReturn("playerOne");
			Mockito.when(playerTwo.getId()).thenReturn("playerTwo");
			Mockito.when(playerOne.getMoveStrategy()).thenReturn(RandomMoveStrategy.INSTANCE);
			Mockito.when(playerTwo.getMoveStrategy()).thenReturn(GreedyMoveStrategy.INSTANCE);
			
			List<Player> players = new ArrayList<>();
			players.add(playerOne);
			players.add(playerTwo);
			
			TournamentRound round = Mockito.mock(TournamentRound.class);
			Mockito.when(round.getPlayers()).thenReturn(players);
			Mockito.when(round.getPlayerWithId("playerOne")).thenReturn(playerOne);
			Mockito.when(round.getPlayerWithId("playerTwo")).thenReturn(playerTwo);
			Mockito.when(round.getPoints("playerOne")).thenReturn(5);
			Mockito.when(round.getPoints("playerTwo")).thenReturn(7);
			
			rounds.add(round);
		}
		
		Tournament tournament = Mockito.mock(Tournament.class);
		Mockito.when(tournament.getTournamentRounds()).thenReturn(rounds);
		
		// --- test ---
		List<MoveStrategy> rankedStrategies = SimpleResultRanker.INSTANCE.rankStrategies(tournament);
		Assert.assertEquals(2, rankedStrategies.size());
		Assert.assertEquals(GreedyMoveStrategy.INSTANCE, rankedStrategies.get(0));
		Assert.assertEquals(RandomMoveStrategy.INSTANCE, rankedStrategies.get(1));
	}
}
