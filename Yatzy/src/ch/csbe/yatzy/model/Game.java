package ch.csbe.yatzy.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

	private int roll = 0;
	private int round = 0;
	private List<Tableau> tableaus = new ArrayList<Tableau>();
	private Tableau actualTableau;
	private Map<String, Dice> dices = new HashMap<String, Dice>();

	public Game(Player player1, Player player2) {
		tableaus.add(new Tableau(player1));
		tableaus.add(new Tableau(player2));
		changeTableau();

	}

	public void roll() {
		if (roll < 3)
			for (Dice dice : dices.values()) {
				if (!dice.isFixed())
					dice.roll();
			}
		roll++;
	}
	
	public int whichTableau(){
		return tableaus.indexOf(actualTableau);
	}

	public void changeTableau() {
		if (actualTableau == null || tableaus.indexOf(actualTableau) == 1) {
			actualTableau = tableaus.get(0);
		} else {
			actualTableau = tableaus.get(1);
		}
		for (int i = 0; i < 5; i++) {
			dices.put("dice" +(i+1), new Dice());
			//dices.get("dice"+(i+1)).setFixed(false);
		}
		round++;
		roll = 0;
	}

	public void setType(String type, int value) {
		try {
			Method method = Tableau.class.getDeclaredMethod("set" + type.substring(0, 1).toUpperCase() + type.substring(1), int.class);
			method.invoke(actualTableau, value);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tableau getActualTableau() {
		return actualTableau;
	}

	public Map<String, Dice> getDices() {
		return dices;
	}

	public int getRound() {
		return round/2;
	}

	public int getRoll() {
		return roll;
	}
	
	public List<Tableau> getTableaus(){
		return this.tableaus;
	}

}
