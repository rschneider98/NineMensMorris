package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class IsMillTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testValidMill() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsMill(0));
		assertTrue(myBoard.IsMill(1));
		assertTrue(myBoard.IsMill(2));
	}
	
	
	public void testInvalidMill() {
		assertFalse(myBoard.IsMill(0));
	}	
	
	public void testFormerMill() {
		// place all pieces
		// form a mill for player 1
		// move out of mill and test this mill
		try {
			// ToDo make game moves
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsMill(0));
		assertTrue(myBoard.IsMill(1));
		assertTrue(myBoard.IsMill(2));
	}
}
