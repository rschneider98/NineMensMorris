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
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
			myBoard.RemoveMan(new Move(0, 9, true));
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testInvalidRemoval() throws Exception {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.RemoveMan(new Move(0, 9, true));
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
}