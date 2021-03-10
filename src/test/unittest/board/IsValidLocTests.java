package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class IsValidLocTests extends TestCase {
private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	public void testValidLoc() {
		assertTrue(myBoard.IsValidLoc(0));
		assertTrue(myBoard.IsValidLoc(23));
	}
	
	public void testLargerLoc() {
		assertTrue(myBoard.IsValidLoc(24));
	}
	
	public void testNegaitiveLoc() {
		assertTrue(myBoard.IsValidLoc(-1));
	}
}
