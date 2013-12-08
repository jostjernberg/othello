package kth.game.tournament.result;

import java.util.List;

import kth.game.othello.player.movestrategy.MoveStrategy;
import kth.game.tournament.Tournament;

public interface ResultRanker {
	
	public List<MoveStrategy> rankStrategies(Tournament tournament);
}
