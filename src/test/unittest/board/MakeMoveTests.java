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
	
	
	public void testInvalidMove() {
		try {
			myBoard.MakeMove(0, 24);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	public void testValidMoveNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testInvalidMoveNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 24));
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
}