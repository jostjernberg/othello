package kth.game.tournament.result;

import kth.game.tournament.Tournament;

/**
 * The responsibility of this entity is to present the results of a tournament.
 */
public interface ResultPresenter {

	/**
	 * Present the results of a tournament.
	 */
	public void present(Tournament tournament);

}
