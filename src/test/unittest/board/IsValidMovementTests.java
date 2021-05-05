package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class IsValidMovementTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	public void testValidPlacement() {
		assertTrue(myBoard.IsValidMovement(0, 0));
	}
	
	
	public void testValidMovement() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			
			myBoard.MakeMove(0, 2);
			myBoard.MakeMove(1, 9);
			
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 5);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 10);
			
			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 22);
			
			myBoard.MakeMove(0, 11);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 7);
			myBoard.MakeMove(1, 8);
			
			myBoard.MakeMove(0, 13);
			myBoard.MakeMove(1, 14);
			
			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 15);
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsValidMovement(0, 19, 18));
	}
	
	
	public void testValidFlying() {
		// Have moves to get a player down to 3 pieces, then check flying
		try {
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 16);
			
			myBoard.MakeMove(0, 5); // formed mill
			myBoard.RemoveMan(0, 16); // remove piece
			myBoard.MakeMove(1, 11);

			myBoard.MakeMove(0, 10);
			myBoard.MakeMove(1, 7);

			myBoard.MakeMove(0, 18); // formed mill
			myBoard.RemoveMan(0, 6); // remove piece
			myBoard.MakeMove(1, 15);

			myBoard.MakeMove(0, 6);
			myBoard.MakeMove(1, 16);

			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 17); // formed mill
			myBoard.RemoveMan(1, 6); // remove piece

			myBoard.MakeMove(0, 9);
			myBoard.MakeMove(1, 6); // formed mill
			myBoard.RemoveMan(1, 9); // remove piece

			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 12);

			myBoard.MakeMove(0, 9, 10); // formed mill
			myBoard.RemoveMan(0, 7); // remove piece
			myBoard.MakeMove(1, 7, 6);

			myBoard.MakeMove(0, 10, 9); // formed mill
			myBoard.RemoveMan(0, 11); // remove piece
			myBoard.MakeMove(1, 11, 15);

			myBoard.MakeMove(0, 9, 10); // formed mill
			myBoard.RemoveMan(0, 11); // remove piece
			myBoard.MakeMove(1, 6, 7);

			myBoard.MakeMove(0, 10, 9); // formed mill
			myBoard.RemoveMan(0, 6); // remove piece
			
			// Player 2 only has 3 pieces left, attempt to fly
			myBoard.MakeMove(1, 9, 16);
		} catch (Exception e) {
			fail();
		}
	}
	

	
	public void testInvalidPlacementNoPieces() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			
			myBoard.MakeMove(0, 2);
			myBoard.MakeMove(1, 9);
			
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 5);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 10);
			
			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 22);
			
			myBoard.MakeMove(0, 11);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 7);
			myBoard.MakeMove(1, 8);
			
			myBoard.MakeMove(0, 13);
			myBoard.MakeMove(1, 14);
			
			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 15);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 9));
	}
	
	
	public void testInvalidPlacementNotEmpty() {
		try {
			myBoard.MakeMove(0, 0);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(1, 0));
	}
	
	
	public void testInvalidMovementPiecesLeft() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			myBoard.MakeMove(0, 13);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(1, 2, 1));
	}
	
	
	public void testInvalidMovementNonEmptyTo() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			
			myBoard.MakeMove(0, 2);
			myBoard.MakeMove(1, 9);
			
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 5);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 10);
			
			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 22);
			
			myBoard.MakeMove(0, 11);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 7);
			myBoard.MakeMove(1, 8);
			
			myBoard.MakeMove(0, 13);
			myBoard.MakeMove(1, 14);
			
			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 15);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 1, 0));
	}
	
	
	public void testInvalidMovementEmptyFrom() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			
			myBoard.MakeMove(0, 2);
			myBoard.MakeMove(1, 9);
			
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 5);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 10);
			
			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 22);
			
			myBoard.MakeMove(0, 11);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 7);
			myBoard.MakeMove(1, 8);
			
			myBoard.MakeMove(0, 13);
			myBoard.MakeMove(1, 14);
			
			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 15);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 20, 19));
	}
	
	
	public void testInvalidFlying() {
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
			
			myBoard.MakeMove(0, 2);
			myBoard.MakeMove(1, 9);
			
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 5);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 10);
			
			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 22);
			
			myBoard.MakeMove(0, 11);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 7);
			myBoard.MakeMove(1, 8);
			
			myBoard.MakeMove(0, 13);
			myBoard.MakeMove(1, 14);
			
			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 15);
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 20, 0));
	}
	
	public void testValidMovementNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			
			myBoard.MakeMove(new Move(0, 2));
			myBoard.MakeMove(new Move(1, 9));
			
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 5));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 10));
			
			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 22));
			
			myBoard.MakeMove(new Move(0, 11));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 7));
			myBoard.MakeMove(new Move(1, 8));
			
			myBoard.MakeMove(new Move(0, 13));
			myBoard.MakeMove(new Move(1, 14));
			
			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 15));
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsValidMovement(0, 19, 18));
	}
	
	
	public void testValidFlyingNewMove() {
		// Have moves to get a player down to 3 pieces, then check flying
		try {
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 16));
			
			myBoard.MakeMove(new Move(0, 5)); // formed mill
			myBoard.RemoveMan(new Move(0, 16, true)); // remove piece
			myBoard.MakeMove(new Move(1, 11));

			myBoard.MakeMove(new Move(0, 10));
			myBoard.MakeMove(new Move(1, 7));

			myBoard.MakeMove(new Move(0, 18)); // formed mill
			myBoard.RemoveMan(new Move(0, 6, true)); // remove piece
			myBoard.MakeMove(new Move(1, 15));

			myBoard.MakeMove(new Move(0, 6));
			myBoard.MakeMove(new Move(1, 16));

			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 17)); // formed mill
			myBoard.RemoveMan(new Move(1, 6, true)); // remove piece

			myBoard.MakeMove(new Move(0, 9));
			myBoard.MakeMove(new Move(1, 6)); // formed mill
			myBoard.RemoveMan(new Move(1, 9, true)); // remove piece

			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 12));

			myBoard.MakeMove(new Move(0, 9, 10)); // formed mill
			myBoard.RemoveMan(new Move(0, 7,true)); // remove piece
			myBoard.MakeMove(new Move(1, 7, 6));

			myBoard.MakeMove(new Move(0, 10, 9)); // formed mill
			myBoard.RemoveMan(new Move(0, 11, true)); // remove piece
			myBoard.MakeMove(new Move(1, 11, 15));

			myBoard.MakeMove(new Move(0, 9, 10)); // formed mill
			myBoard.RemoveMan(new Move(0, 11, true)); // remove piece
			myBoard.MakeMove(new Move(1, 6, 7));

			myBoard.MakeMove(new Move(0, 10, 9)); // formed mill
			myBoard.RemoveMan(new Move(0, 6,true)); // remove piece
			
			// Player 2 only has 3 pieces left, attempt to fly
			myBoard.MakeMove(new Move(1, 9, 16));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	
	public void testInvalidTurn() {
		assertFalse(myBoard.IsValidMovement(1, 0));
	}
	
	
	public void testInvalidPlacementNoPiecesNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			
			myBoard.MakeMove(new Move(0, 2));
			myBoard.MakeMove(new Move(1, 9));
			
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 5));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 10));
			
			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 22));
			
			myBoard.MakeMove(new Move(0, 11));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 7));
			myBoard.MakeMove(new Move(1, 8));
			
			myBoard.MakeMove(new Move(0, 13));
			myBoard.MakeMove(new Move(1, 14));
			
			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 15));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 9));
	}
	
	
	public void testInvalidPlacementNotEmptyNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(1, 0));
	}
	
	
	public void testInvalidMovementPiecesLeftNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			myBoard.MakeMove(new Move(0, 13));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(1, 2, 1));
	}
	
	
	public void testInvalidMovementNonEmptyToNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			
			myBoard.MakeMove(new Move(0, 2));
			myBoard.MakeMove(new Move(1, 9));
			
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 5));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 10));
			
			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 22));
			
			myBoard.MakeMove(new Move(0, 11));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 7));
			myBoard.MakeMove(new Move(1, 8));
			
			myBoard.MakeMove(new Move(0, 13));
			myBoard.MakeMove(new Move(1, 14));
			
			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 15));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 1, 0));
	}
	
	
	public void testInvalidMovementEmptyFromNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			
			myBoard.MakeMove(new Move(0, 2));
			myBoard.MakeMove(new Move(1, 9));
			
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 5));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 10));
			
			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 22));
			
			myBoard.MakeMove(new Move(0, 11));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 7));
			myBoard.MakeMove(new Move(1, 8));
			
			myBoard.MakeMove(new Move(0, 13));
			myBoard.MakeMove(new Move(1, 14));
			
			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 15));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 20, 19));
	}
	
	
	public void testInvalidFlyingNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
			
			myBoard.MakeMove(new Move(0, 2));
			myBoard.MakeMove(new Move(1, 9));
			
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 5));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 10));
			
			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 22));
			
			myBoard.MakeMove(new Move(0, 11));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 7));
			myBoard.MakeMove(new Move(1, 8));
			
			myBoard.MakeMove(new Move(0, 13));
			myBoard.MakeMove(new Move(1, 14));
			
			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 15));
		} catch (Exception e) {
			fail();
		}
		assertFalse(myBoard.IsValidMovement(0, 20, 0));
	}
	
	
	public void testInvalidLoc() {
		assertFalse(myBoard.IsValidMovement(0, 24));
	}
	
	
	public void testInvalidLocNotNull() {
		assertFalse(myBoard.IsValidMovement(0, 21, 25));
	}
}