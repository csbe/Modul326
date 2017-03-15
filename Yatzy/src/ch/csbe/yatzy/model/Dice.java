package ch.csbe.yatzy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.persistence.Entity;

import javafx.scene.image.Image;

@Entity
public class Dice implements Comparable<Dice> {

	private int value = 0;
	private boolean fixed = false;
	private Image image;

	private static final Map<Integer, Image> IMAGES;
	static {
		String s = "/Resources/";
		IMAGES = new HashMap<Integer, Image>();
		try{
			IMAGES.put(1, new Image(Dice.class.getResourceAsStream(s+"dice-1-md.png")));
			IMAGES.put(2, new Image(Dice.class.getResourceAsStream(s+"dice-2-md.png")));
			IMAGES.put(3, new Image(Dice.class.getResourceAsStream(s+"dice-3-md.png")));
			IMAGES.put(4, new Image(Dice.class.getResourceAsStream(s+"dice-4-md.png")));
			IMAGES.put(5, new Image(Dice.class.getResourceAsStream(s+"dice-5-md.png")));
			IMAGES.put(6, new Image(Dice.class.getResourceAsStream(s+"dice-6-md.png")));
		}catch(Exception e){
			
		}
	}

	public Dice() {
		super();
	}
	
	public void roll(){
		Random random = new Random();
		value = random.nextInt(6)+1;
		image = IMAGES.get(value);
	}
	

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}

	public int getValue() {
		return value;
	}

	public Image getImage() {
		return image;
	}

	public int compareTo(Dice o) {
		if(value > o.getValue())
			return 1;
		else if(value < o.getValue())
			return -1;
		return 0;
	}
	
	public Dice setValue(int i){
		this.value = i;
		return this;
	}

}
