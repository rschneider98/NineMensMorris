package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class AreAdjacentTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	public void testInitialState() {
		assertTrue(myBoard.AreAdjacent(0, 1));
		assertFalse(myBoard.AreAdjacent(0, 5));
	}
}
