package kth.game.othello.board;

import java.util.Observer;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NodeImplTest {

	@Test
	public void observerTest() {
		// TODO
		NodeImpl n1 = new NodeImpl(1, 2);
		NodeImpl n2 = new NodeImpl(1, 2, "playerId1");

		Observer obs1 = Mockito.mock(Observer.class);
		Observer obs2 = Mockito.mock(Observer.class);
		n1.addObserver(obs1);
		n2.addObserver(obs1);

		n1.setOccupantPlayer("playerId2");
		n2.setOccupantPlayer("playerId2");

		Mockito.verify(obs1).update(n1, null);
		Mockito.verify(obs1).update(n2, "playerId1");

		n2.addObserver(obs2);

		n2.setOccupantPlayer("playerId3");
		Mockito.verify(obs1).update(n1, null);
		Mockito.verify(obs1).update(n2, "playerId2");
		Mockito.verify(obs2).update(n2, "playerId2");
	}

	@Test
	public void uniqueIdTest() {
		Node n1 = new NodeImpl(0, 0);
		Node n2 = new NodeImpl(0, 0);
		Assert.assertNotEquals(n1.getId(), n2.getId());
	}
}
