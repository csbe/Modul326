package ch.csbe.yatzy.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.csbe.yatzy.helper.GameHelper;
import ch.csbe.yatzy.model.Dice;

public class HelperTest {

	Map<String,Dice> dices;
	
	@Before
	public void setUp() throws Exception {
		
		
		
	}
	
	@Test
	public void testIsYatzee() {
		dices = new HashMap<String,Dice>();
		dices.put("dice1", new Dice().setValue(2));
		dices.put("dice2", new Dice().setValue(2));
		dices.put("dice3", new Dice().setValue(3));
		dices.put("dice4", new Dice().setValue(4));
		dices.put("dice5", new Dice().setValue(2));
		assertEquals(true, GameHelper.isCount(dices, 3));
	}
	
	
	@Test
	public void testIsFullHouse() {
		dices = new HashMap<String,Dice>();
		dices.put("dice1", new Dice().setValue(5));
		dices.put("dice2", new Dice().setValue(2));
		dices.put("dice3", new Dice().setValue(2));
		dices.put("dice4", new Dice().setValue(2));
		dices.put("dice5", new Dice().setValue(5));
		assertEquals(true, GameHelper.isFullHouse(dices));
	}

	@Test
	public void testIsSmallStreet() {
		dices = new HashMap<String,Dice>();
		dices.put("dice1", new Dice().setValue(4));
		dices.put("dice2", new Dice().setValue(2));
		dices.put("dice3", new Dice().setValue(3));
		dices.put("dice4", new Dice().setValue(4));
		dices.put("dice5", new Dice().setValue(5));
		assertEquals(true, GameHelper.isSmallStreet(dices));
	}

	@Test
	public void testIsBigStreet() {
		dices = new HashMap<String,Dice>();
		dices.put("dice1", new Dice().setValue(4));
		dices.put("dice2", new Dice().setValue(2));
		dices.put("dice3", new Dice().setValue(3));
		dices.put("dice4", new Dice().setValue(1));
		dices.put("dice5", new Dice().setValue(5));
		assertEquals(true, GameHelper.isBigStreet(dices));
	}

}
