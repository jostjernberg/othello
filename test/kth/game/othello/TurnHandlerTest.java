package kth.game.othello;

import java.util.ArrayList;
import java.util.List;

import kth.game.othello.player.Player;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TurnHandlerTest {

	private Player mockPlayer(String playerId) {
		Player p = Mockito.mock(Player.class);

		Mockito.when(p.getId()).thenReturn(playerId);

		return p;
	}

	@Test
	public void canStartAndCorrectlyPassTurnToNextPlayerTest() {
		List<Player> players = new ArrayList<>();
		players.add(mockPlayer("player1"));
		players.add(mockPlayer("player2"));
		players.add(mockPlayer("player3"));
		Rules rules = Mockito.mock(Rules.class);
		Mockito.when(rules.hasValidMove("player1")).thenReturn(false);
		Mockito.when(rules.hasValidMove("player2")).thenReturn(true);
		Mockito.when(rules.hasValidMove("player3")).thenReturn(true);

		TurnHandler th = new TurnHandler(rules, players);
		th.start("player1");
		Assert.assertEquals("player1", th.getPlayerInTurn().getId());
		th.passTurnToNextPlayer();
		Assert.assertEquals("player2", th.getPlayerInTurn().getId());
		th.passTurnToNextPlayer();
		Assert.assertEquals("player3", th.getPlayerInTurn().getId());
		th.passTurnToNextPlayer();
		Assert.assertEquals("player2", th.getPlayerInTurn().getId()); // player 1 is not able to make a move, pass turn
																		// to player 2
	}

	@Test
	public void shouldNotBeActiveTest() {
		List<Player> players = new ArrayList<>();
		players.add(mockPlayer("player1"));
		players.add(mockPlayer("player2"));
		players.add(mockPlayer("player3"));
		Rules rules = Mockito.mock(Rules.class);
		Mockito.when(rules.hasValidMove("player1")).thenReturn(false);
		Mockito.when(rules.hasValidMove("player2")).thenReturn(false);
		Mockito.when(rules.hasValidMove("player3")).thenReturn(false);

		TurnHandler th = new TurnHandler(rules, players);
		Assert.assertFalse(th.isActive());
	}

	@Test
	public void shouldBeActiveTest() {
		List<Player> players = new ArrayList<>();
		players.add(mockPlayer("player1"));
		players.add(mockPlayer("player2"));
		players.add(mockPlayer("player3"));
		Rules rules = Mockito.mock(Rules.class);
		Mockito.when(rules.hasValidMove("player1")).thenReturn(true);
		Mockito.when(rules.hasValidMove("player2")).thenReturn(true);
		Mockito.when(rules.hasValidMove("player3")).thenReturn(false);

		TurnHandler th = new TurnHandler(rules, players);
		Assert.assertTrue(th.isActive());
	}

	@Test
	public void startTest() {
		List<Player> players = new ArrayList<>();
		players.add(mockPlayer("player1"));
		players.add(mockPlayer("player2"));
		players.add(mockPlayer("player3"));
		Rules rules = Mockito.mock(Rules.class);

		TurnHandler th = new TurnHandler(rules, players);
		th.start("player2");
		Assert.assertEquals("player2", th.getPlayerInTurn().getId());
	}
}
