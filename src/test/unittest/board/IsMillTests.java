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
	public void testFormerMill() {
		// place all pieces
		// form a mill for player 1
		// move out of mill and test this mill
		try {
			myBoard.MakeMove(0, 3);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 16);
			
			myBoard.MakeMove(0, 5); // formed mill
			myBoard.RemoveMan(0,  16); // remove piece
			myBoard.MakeMove(1, 11);

			myBoard.MakeMove(0, 10);
			myBoard.MakeMove(1, 7);

			myBoard.MakeMove(0, 18); // formed mill
			myBoard.RemoveMan(0,  6); // remove piece
			myBoard.MakeMove(1, 15);

			myBoard.MakeMove(0, 6);
			myBoard.MakeMove(1, 16);

			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 17); // formed mill
			myBoard.RemoveMan(1,  6); // remove piece

			myBoard.MakeMove(0, 9);
			myBoard.MakeMove(1, 6); // formed mill
			myBoard.RemoveMan(1,  9); // remove piece

			myBoard.MakeMove(0, 21);
			myBoard.MakeMove(1, 12);

			myBoard.MakeMove(0, 9, 10); // formed mill
			myBoard.RemoveMan(0,  7); // remove piece
		} catch (Exception e) {
			fail();
		}
		
		// test former mill for player 1: {3, 10, 18} (not a mill)
		// NOTE: 3 is part of a mill {3, 4, 5}
		assertTrue(myBoard.IsMill(3));
		assertFalse(myBoard.IsMill(10));
		assertFalse(myBoard.IsMill(18));
		
		// test new mill for player 1: {0, 9, 21} (mill)
		assertTrue(myBoard.IsMill(0));
		assertTrue(myBoard.IsMill(9));
		assertTrue(myBoard.IsMill(21));
	}
	public void testValidMillNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
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
	
	public void testFormerMillNewMove() {
		// place all pieces
		// form a mill for player 1
		// move out of mill and test this mill
		try {
			myBoard.MakeMove(new Move(0, 3));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 16));
			
			myBoard.MakeMove(new Move(0, 5)); // formed mill
			myBoard.RemoveMan(new Move(0,  16, true)); // remove piece
			myBoard.MakeMove(new Move(1, 11));

			myBoard.MakeMove(new Move(0, 10));
			myBoard.MakeMove(new Move(1, 7));

			myBoard.MakeMove(new Move(0, 18)); // formed mill
			myBoard.RemoveMan(new Move(0,  6, true)); // remove piece
			myBoard.MakeMove(new Move(1, 15));

			myBoard.MakeMove(new Move(0, 6));
			myBoard.MakeMove(new Move(1, 16));

			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 17)); // formed mill
			myBoard.RemoveMan(new Move(1,  6, true)); // remove piece

			myBoard.MakeMove(new Move(0, 9));
			myBoard.MakeMove(new Move(1, 6)); // formed mill
			myBoard.RemoveMan(new Move(1,  9, true)); // remove piece

			myBoard.MakeMove(new Move(0, 21));
			myBoard.MakeMove(new Move(1, 12));

			myBoard.MakeMove(new Move(0, 9, 10)); // formed mill
			myBoard.RemoveMan(new Move(0,  7, true)); // remove piece
		} catch (Exception e) {
			fail();
		}
		
		// test former mill for player 1: {3, 10, 18} (not a mill)
		// NOTE: 3 is part of a mill {3, 4, 5}
		assertTrue(myBoard.IsMill(3));
		assertFalse(myBoard.IsMill(10));
		assertFalse(myBoard.IsMill(18));
		
		// test new mill for player 1: {0, 9, 21} (mill)
		assertTrue(myBoard.IsMill(0));
		assertTrue(myBoard.IsMill(9));
		assertTrue(myBoard.IsMill(21));
	}
}
