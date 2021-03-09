package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class RemoveManTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testValidRemoval() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
			myBoard.RemoveMan(0, 9);
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testInvalidRemoval() throws Exception {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.RemoveMan(0, 9);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}