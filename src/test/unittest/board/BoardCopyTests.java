package test.unittest.board;

import junit.framework.TestCase;
import main.game.Board;

public class BoardCopyTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard=new Board();
		
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
		
	}
	public void testCopy() {
		Board copyBoard=new Board(myBoard);
		
		assertTrue(copyBoard.equals(myBoard));
	}
	public void testEqualsInavalid() {
		Board copyBoard=new Board(myBoard);
		
		try {
			copyBoard.MakeMove(1, 8, 12);
		} catch (Exception e) {
			fail();
		}
		
		assertFalse(copyBoard.equals(myBoard));
		
	
	}
	public void testEqualsValidAfterMove() {
		Board copyBoard=new Board(myBoard);
		
		try {
			copyBoard.MakeMove(1, 8, 12);
			myBoard.MakeMove(1, 8, 12);
		} catch (Exception e) {
			fail();
		}
		
		//assertTrue(copyBoard.equals(myBoard));
		
	}
}
