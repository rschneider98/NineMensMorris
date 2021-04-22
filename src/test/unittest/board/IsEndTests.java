package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class IsEndTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	
	public void testInitialState() {
		assertFalse(myBoard.IsEnd());
	}
	
	
	public void testTwoPiecesLeft() {
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
		assertTrue(myBoard.IsEnd());
	}
	
	public void testNoLegalMovesNewMove() {
		try {
			myBoard.MakeMove(new Move(0, 10));
			myBoard.MakeMove(new Move(1, 6));
			
			myBoard.MakeMove(new Move(0, 4));
			myBoard.MakeMove(new Move(1, 7));
			
			myBoard.MakeMove(new Move(0, 13)); 
			myBoard.MakeMove(new Move(1, 11));

			myBoard.MakeMove(new Move(0, 19));
			myBoard.MakeMove(new Move(1, 16));

			myBoard.MakeMove(new Move(0, 18));
			myBoard.MakeMove(new Move(1, 17));

			myBoard.MakeMove(new Move(0, 5));
			myBoard.MakeMove(new Move(1, 12));

			myBoard.MakeMove(new Move(0, 9));
			myBoard.MakeMove(new Move(1, 15)); // formed mill
			myBoard.RemoveMan(new Move(1,  9, true)); // remove piece

			myBoard.MakeMove(new Move(0, 9));
			myBoard.MakeMove(new Move(1, 8)); // formed mill
			myBoard.RemoveMan(new Move(1,  9, true)); // remove piece

			myBoard.MakeMove(new Move(0, 9));
			myBoard.MakeMove(new Move(1, 0));

			myBoard.MakeMove(new Move(0, 3, 10)); // formed mill
			myBoard.RemoveMan(new Move(0,  0, true)); // remove piece
			myBoard.MakeMove(new Move(1, 10, 11));
			
			myBoard.MakeMove(new Move(0, 0, 9)); 
			myBoard.MakeMove(new Move(1, 11, 10)); // formed mill
			myBoard.RemoveMan(new Move(1, 0, true)); // remove piece
			
			myBoard.MakeMove(new Move(0, 10, 18)); // player 2 is now boxed in with no legal moves
		} catch (Exception e) {
			fail();
		}
		assertTrue(myBoard.IsEnd());
	}
}