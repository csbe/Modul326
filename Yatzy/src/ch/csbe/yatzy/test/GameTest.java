/**
 * 
 */
package ch.csbe.yatzy.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import ch.csbe.yatzy.model.Dice;
import ch.csbe.yatzy.model.Game;
import ch.csbe.yatzy.model.Player;

/**
 * @author enrico.buchs
 *
 */
public class GameTest {

	/**
	 * Test method for {@link ch.csbe.yatzy.model.Game#roll()}.
	 */
	@Test
	public void testRoll() {
		Game game = new Game(new Player("Max"), new Player("Hans"));
		int value = game.getDices().get("dice1").getValue();
		game.roll();
		int value1 = game.getDices().get("dice1").getValue();
		assertNotEquals(value, value1);
		game.roll();
		int value2 = game.getDices().get("dice1").getValue();
		assertNotEquals(value1, value2);
	}

	/**
	 * Test method for {@link ch.csbe.yatzy.model.Game#changeTableau()}.
	 */
	@Test
	public void testChangeTableau() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.csbe.yatzy.model.Game#setType(java.lang.String, int)}.
	 */
	@Test
	public void testSetType() {
		fail("Not yet implemented");
	}

}
