package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

public class NodeImpl extends Observable implements Node {
	private UUID id = UUID.randomUUID();
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;
	private List<Observer> observers = new ArrayList<>();

	NodeImpl(int x, int y, String occupantPlayerId) {
		this(x, y);
		this.occupantPlayerId = occupantPlayerId;
	}

	NodeImpl(int x, int y) {
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	/**
	 * Sets this node's occupant player and notifies the node's observers.
	 * 
	 * @param occupantPlayerId
	 *            The id of the new occupant player.
	 */
	public void setOccupantPlayer(String occupantPlayerId) {
		String previousOccupantPlayerId = this.occupantPlayerId;
		this.occupantPlayerId = occupantPlayerId;
		notifyObservers(previousOccupantPlayerId);
	}

	/**
	 * Notify all observers of this node that it has a new occupant player.
	 * 
	 * @param newOccupantPlayerId
	 *            The id of the new occupant player.
	 */
	private void notifyObservers(String previousOccupantPlayerId) {
		for (Observer obs : observers) {
			obs.update(this, previousOccupantPlayerId);
		}
	}

	@Override
	public String getId() {
		return id.toString();
	}

	@Override
	public String getOccupantPlayerId() {
		return occupantPlayerId;
	}

	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	@Override
	public boolean isMarked() {
		return occupantPlayerId != null;
	}
}
