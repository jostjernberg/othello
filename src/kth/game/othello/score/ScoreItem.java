package kth.game.othello.score;

/**
 * An instance of this class contains the score of a player.
 * 
 * @author tomasekholm
 */
public class ScoreItem implements Comparable<ScoreItem> {

	private String playerId;
	private int score;

	public ScoreItem(String playerId, int score) {
		this.playerId = playerId;
		this.score = score;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(ScoreItem scoreItem) {
		if (getScore() < scoreItem.getScore()) {
			return -1;
		} else if (getScore() > scoreItem.getScore()) {
			return 1;
		}
		return 0;
	}
}
