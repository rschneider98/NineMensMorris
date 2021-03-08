package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class IsValidRemovalTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testValidRemoval() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsValidRemoval(0, 9));
	}
	
	
	public void testInvalidTurn() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidRemoval(1, 9));
	}
	
	
	public void testEmptyLocation() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidRemoval(0, 10));
	}
	
	
	public void testOwnPiece() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidRemoval(0, 0));
	}
}

