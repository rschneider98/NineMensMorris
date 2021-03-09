package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class MakeMoveTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testValidMove() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testInvalidMove() throws Exception {
		myBoard.MakeMove(0, 24);
	}
}