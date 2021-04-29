package test.unittest.board;

import junit.framework.TestCase;
import main.game.Board;

public class fromFileTests extends TestCase{
	Board myBoard;
	
	public void testLoadBoard() {
		myBoard = new Board();
		
		try {
			myBoard.fromFile("gameBoard429");
		} catch (Exception e) {
			fail();
		}
		
	
		
	}
	
}
