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
	
	public void testEveryPieceAMill() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 3);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 10);
			myBoard.MakeMove(0, 2);
			
			
			
		} catch (Exception e) {
			
			fail();
		}
		assertTrue(myBoard.everyPieceAMill(0));
		assertFalse(myBoard.everyPieceAMill(1));
	}
	public void testValidRemovalNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
		} catch (Exception e) {
			fail();
		}
		Move currentRemoval=new Move(0, 9, true);
		assertTrue(myBoard.IsValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
	}
	
	
	public void testInvalidTurnNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
		} catch (Exception e) {
			fail();
		}
		Move currentRemoval=new Move(1, 9, true);
		assertFalse(myBoard.IsValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
	}
	
	
	public void testEmptyLocationNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
		} catch (Exception e) {
			fail();
		}
		Move currentRemoval=new Move(0, 10, true);
		assertFalse(myBoard.IsValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
	}
	
	
	public void testOwnPieceNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
		} catch (Exception e) {
			fail();
		}
		Move currentRemoval=new Move(0, 0, true);
		assertFalse(myBoard.IsValidRemoval(currentRemoval.getPlayerTurn(),currentRemoval.getLocationTo()));
	}
	
	public void testEveryPieceAMillNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 3));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 10));
			myBoard.MakeMove(new Move(0, 2));
			
			
			
		} catch (Exception e) {
			
			fail();
		}
		assertTrue(myBoard.everyPieceAMill(0));
		assertFalse(myBoard.everyPieceAMill(1));
	}
}

