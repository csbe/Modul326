package ch.csbe.yatzy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import ch.csbe.yatzy.helper.MapUtil;

public class Highscore implements Serializable{

	private static final long serialVersionUID = 1L;
	private Map<Player,Integer> scores = new HashMap<Player,Integer>();
	
	public Highscore(){
		super();
	}
	
	public Highscore(Map<Player,Integer> scores){
		this.scores = scores;
	}
	
	public void add(Player p, int score){
		scores.put(p, score);
	}
	
	public Map<Player,Integer> result(){
		return MapUtil.sortByValueDesc( scores );
	}

	public Map<Player, Integer> getScores() {
		return scores;
	}

	public void setScores(Map<Player, Integer> scores) {
		this.scores = scores;
	}
		
}
