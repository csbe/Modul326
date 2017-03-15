package ch.csbe.yatzy.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.csbe.yatzy.model.Dice;

public class GameHelper {

	public static enum FIXEDPOINTS {
		SmallStreet, BigStreet, Yatzee, FullHouse
	}

	public static FIXEDPOINTS fixed;
	
	public static int calculateSum(Map<String, Dice> dices){
		int sum = 0;
		for(Dice d : dices.values()){
			sum += d.getValue();
		}
		return sum;
	}
	
	public static int calculateMore(Map<String, Dice> dices, int count){
		List<Dice> diceslist = new ArrayList<Dice>(dices.values());
		Collections.sort(diceslist);
		
		Map<Integer,Integer> sum = new HashMap<Integer,Integer>();
		
		Arrays.stream(diceslist.toArray())
	      .collect(Collectors.groupingBy(s -> ((Dice)s).getValue()))
	      .forEach((k, v) -> sum.put(v.size(),k));
		
		if(sum.containsKey(count))
			return count*sum.get(count);
	    return 0;
	}
	
	
	public static int calculate(Map<String, Dice> dices, int value){
		int count = 0;
		for(Dice d : dices.values()){
			if(d.getValue() == value)
				count++;
		}
		
		return value*count;
	}
	
	
	
	public static boolean isCount(Map<String, Dice> dices, int count) {
		List<Dice> diceslist = new ArrayList<Dice>(dices.values());
		Collections.sort(diceslist);
		
		Map<Integer,Integer> sum = new HashMap<Integer,Integer>();
		
		Arrays.stream(diceslist.toArray())
	      .collect(Collectors.groupingBy(s -> ((Dice)s).getValue()))
	      .forEach((k, v) -> sum.put(k, v.size()));
		
		if(sum.containsValue(count) || sum.containsValue(count+1) || sum.containsValue(count+2))
			return true;
		
		return false;
	}
	

	public static boolean isFullHouse(Map<String, Dice> dices) {
		int c = dices.size();
		List<Dice> diceslist = new ArrayList<Dice>(dices.values());
		Collections.sort(diceslist);
		Iterator<Dice> iter = diceslist.listIterator();
		int prev = 0;
		int counto = 0;
		int countt = 0;
		while (iter.hasNext()) {
			Dice d = iter.next();
			if (prev == 0) {
				prev = d.getValue();
				iter.remove();
				counto++;
				continue;
			} else {
				if (d.getValue() == prev) {
					prev = d.getValue();
					iter.remove();
					counto++;
				} 
			}
		}
		if (counto >= 2) {
			iter = diceslist.listIterator();
			prev = 0;
			while (iter.hasNext()) {
				Dice d = iter.next();
				if (prev == 0) {
					prev = d.getValue();
					countt++;
					continue;
				} else {
					if (d.getValue() == prev) {
						countt++;
						prev = d.getValue();
						iter.remove();
					}
				}
			}
			if (Math.max(counto, countt) == 3 && Math.min(counto, countt) == 2) {
				fixed = FIXEDPOINTS.FullHouse;
				return true;
			}
		}

		return false;
	}

	public static boolean isSmallStreet(Map<String, Dice> dices) {
		List<Dice> diceslist = new ArrayList<Dice>(dices.values());
		Collections.sort(diceslist);
		int prev = 0;
		int count = 0;
		for (Dice d : diceslist) {
			if (prev == 0) {
				prev = d.getValue();
				count++;
				continue;
			} else {
				if (d.getValue() - prev == 1) {
					count++;
				} else if (d.getValue() == prev) {

				} else {
					count = 0;
				}
				prev = d.getValue();
			}
		}
		if (count > 3) {
			fixed = FIXEDPOINTS.SmallStreet;
			return true;
		}
		return false;
	}

	public static boolean isBigStreet(Map<String, Dice> dices) {
		List<Dice> diceslist = new ArrayList<Dice>(dices.values());
		Collections.sort(diceslist);
		int prev = 0;
		int count = 0;
		for (Dice d : diceslist) {
			if (prev == 0) {
				prev = d.getValue();
				count++;
				continue;
			} else {
				if (d.getValue() - prev == 1) {
					count++;
				} else if (d.getValue() == prev) {

				} else {
					count = 0;
				}
				prev = d.getValue();
			}
		}
		if (count >= 5) {
			fixed = FIXEDPOINTS.BigStreet;
			return true;
		}
		return false;
	}

}
