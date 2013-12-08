package kth.game.tournament;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TournamentRoundTest {

	@Test
	public void getPlayerWithIdTest() {
		// --- setup ---
		Player p1 = Mockito.mock(Player.class);
		Player p2 = Mockito.mock(Player.class);
		Player p3 = Mockito.mock(Player.class);
		Player p4 = Mockito.mock(Player.class);
		Mockito.when(p1.getId()).thenReturn("p1-id");
		Mockito.when(p2.getId()).thenReturn("p2-id");
		Mockito.when(p3.getId()).thenReturn("p3-id");
		Mockito.when(p4.getId()).thenReturn("p4-id");
		
		List<Player> players = new ArrayList<>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		
		Othello othello = Mockito.mock(Othello.class);
		Mockito.when(othello.getPlayers()).thenReturn(players);
	
		// --- test ---
		TournamentRound round = new TournamentRound(othello, null, null);
		Assert.assertEquals(p1, round.getPlayerWithId("p1-id"));
		Assert.assertEquals(p2, round.getPlayerWithId("p2-id"));
		Assert.assertEquals(p3, round.getPlayerWithId("p3-id"));
		Assert.assertEquals(p4, round.getPlayerWithId("p4-id"));
	}
}
