package kth.game.othello.board;

import java.util.Observable;
import java.util.Observer;

class NodeImpl extends Observable implements Node {
	private String id;
	private String occupantPlayerId;
	private int xCoordinate;
	private int yCoordinate;

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
	}

	/**
	 * Sets this node's occupant player and notifies the node's observers.
	 * 
	 * @param occupantPlayerId
	 *            The id of the new occupant player.
	 */
	public void setOccupantPlayer(String occupantPlayerId) {
		this.occupantPlayerId = occupantPlayerId;
		notifyObservers(occupantPlayerId);
	}

	/**
	 * Notify all observers of this node that it has a new occupant player.
	 * 
	 * @param newOccupantPlayerId
	 *            The id of the new occupant player.
	 */
	private void notifyObservers(String newOccupantPlayerId) {
		// TODO
	}

	@Override
	public String getId() {
		return id;
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
