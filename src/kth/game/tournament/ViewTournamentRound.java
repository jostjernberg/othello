package kth.game.tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kth.game.othello.Othello;
import kth.game.othello.player.Player;
import kth.game.othello.score.Score;
import kth.game.othello.view.swing.OthelloView;

public class ViewTournamentRound extends Observable implements TournamentRound{
	List<Observer> observers = new ArrayList<>();
	Othello othello;
	OthelloView othelloView;
	
	public ViewTournamentRound(Othello othello, OthelloView othelloView) {
		this.othello = othello;
		this.othelloView = othelloView;
	}
	
	public Score getScore() {
		return othello.getScore();
	}
	
	public List<Player> getPlayers() {
		return othello.getPlayers();
	}
	
	public Player getPlayerWithId(String playerId) {
		Player player = null;
		
		for(Player p : othello.getPlayers()) {
			if(p.getId().equals(playerId)) {
				player = p;
			}
		}
		
		return player;
	}
	
	@Override
	public void start() {
		othelloView.start();
	}

	@Override
	public void addObserver(Observer obs) {
		observers.add(obs);
	}
}
