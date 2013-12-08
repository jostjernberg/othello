package kth.game.othello.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

/**
 *	The responsibility of this entity is to implement the Node interface.
 */
public class NodeImpl extends Observable implements Node {
	private UUID id = UUID.randomUUID();
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;
	private List<Observer> observers = new ArrayList<>();

	/**
	 * Creates a new node.
	 */
	NodeImpl(int xCoordinate, int yCoordinate, String occupantPlayerId) {
		this(xCoordinate, yCoordinate);
		this.occupantPlayerId = occupantPlayerId;
	}
	
	/**
	 * Creates a new node with occupantPlayerId == null.
	 */
	NodeImpl(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	/**
	 * Sets this node's occupant player and notifies the node's observers.
	 * 
	 * @param occupantPlayerId The id of the new occupant player.
	 */
	public void setOccupantPlayer(String occupantPlayerId) {
		String previousOccupantPlayerId = this.occupantPlayerId;
		this.occupantPlayerId = occupantPlayerId;
		notifyObservers(previousOccupantPlayerId);
	}

	/**
	 * Notify all observers of this node that it has a new occupant player.
	 * 
	 * @param newOccupantPlayerId The id of the new occupant player.
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
		return result;
	}

	/**
	 * Returns true if the other object is an instance of NodeImpl with the same
	 * x- and y-coordinates.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (xCoordinate != other.getXCoordinate())
			return false;
		if (yCoordinate != other.getYCoordinate())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Node[");
		sb.append("occupantPlayerId=").append(getOccupantPlayerId()).append(", ");
		sb.append("xCoordinate=").append(getXCoordinate()).append(", ");
		sb.append("yCoordinate=").append(getYCoordinate()).append(", ");
		sb.append("id=").append(getId()).append("]");
		return sb.toString();
	}
}
