package kth.game.tournament;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.othello.score.Score;
import kth.game.othello.score.ScoreItem;

public class SimpleResultPresenter implements ResultPresenter{
	public static SimpleResultPresenter INSTANCE = new SimpleResultPresenter();

	private SimpleResultPresenter() {
		
	}
	
	@Override
	public void present(List<TournamentRound> tournamentRounds) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tournamentRounds.size(); i++) {
			sb.append("Round ").append(i).append(":\n");
			Score score = tournamentRounds.get(i).getScore();
			for(ScoreItem scoreItem : score.getPlayersScore()) {
				String playerId = scoreItem.getPlayerId();
				String strategy = tournamentRounds.get(i+1).getPlayerWithId(playerId).getMoveStrategy().getName();
				sb.append(strategy).append(": ").append(scoreItem.getScore()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
