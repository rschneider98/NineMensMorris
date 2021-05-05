package test.unittest.board;

import junit.framework.TestCase;
import main.game.Board;
import main.game.GameStates;

public class fromFileTests extends TestCase{
	Board myBoard;
	
	public void testLoadBoard() {
		myBoard = new Board();
		
		try {
			myBoard.fromFile("src/test/unittest/board/testBoard1.json");
		} catch (Exception e) {
			fail();
		}
		
		Integer[] testGrid= {1,1,2,2,0,2,0,1,0,0,2,1,0,1,2,2,0,2,1,2,2,1,1,1};
		int playerTurn=0;
		GameStates gameState=GameStates.remove;
		Integer livePiecesP1=9;
		Integer livePiecesP2=9;
		Integer unplacePiecesP1=0;
		Integer unplacePiecesP2=0;
		
		Integer[] livePieces = {9,9};
		Integer[] unplacedPieces = {0,0};
		Board testBoard;
		try {
			testBoard = new Board(testGrid, playerTurn, gameState,unplacedPieces,livePieces);
			assertTrue(myBoard.equals(testBoard));
		} catch (Exception e) {
			fail();
		}
		
		
		
		
	}
	
}
