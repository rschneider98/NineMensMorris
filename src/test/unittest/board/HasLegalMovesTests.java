package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class HasLegalMovesTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testLegalWithPlacements() {
		assertTrue(myBoard.HasLegalMoves(0));
		assertTrue(myBoard.HasLegalMoves(1));
	}
	
	
	public void testLegalWithMovements() {
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
		assertTrue(myBoard.HasLegalMoves(0));
		assertTrue(myBoard.HasLegalMoves(1));
	}
	
	
	public void testNoLegalMoves() {
		try {
			myBoard.MakeMove(0, 10);
			myBoard.MakeMove(1, 6);
			
			myBoard.MakeMove(0, 4);
			myBoard.MakeMove(1, 7);
			
			myBoard.MakeMove(0, 13); 
			myBoard.MakeMove(1, 11);

			myBoard.MakeMove(0, 19);
			myBoard.MakeMove(1, 16);

			myBoard.MakeMove(0, 18);
			myBoard.MakeMove(1, 17);

			myBoard.MakeMove(0, 5);
			myBoard.MakeMove(1, 12);

			myBoard.MakeMove(0, 9);
			myBoard.MakeMove(1, 15); // formed mill
			myBoard.RemoveMan(1,  9); // remove piece

			myBoard.MakeMove(0, 9);
			myBoard.MakeMove(1, 8); // formed mill
			myBoard.RemoveMan(1,  9); // remove piece

			myBoard.MakeMove(0, 9);
			myBoard.MakeMove(1, 0);

			myBoard.MakeMove(0, 3, 10); // formed mill
			myBoard.RemoveMan(0,  0); // remove piece
			myBoard.MakeMove(1, 10, 11);
			
			myBoard.MakeMove(0, 0, 9); 
			myBoard.MakeMove(1, 11, 10); // formed mill
			myBoard.RemoveMan(1, 0); // remove piece
			
			myBoard.MakeMove(0, 10, 18); // player 2 is now boxed in with no legal moves
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.HasLegalMoves(0));
		assertFalse(myBoard.HasLegalMoves(1));
	}
}
