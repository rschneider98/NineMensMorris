package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class GetScoreTests extends TestCase {	
	public void testScoreValueZero() {
        Integer[] boardGrid=new Integer[] {  
            0,0,0,
            0,0,0,
            0,0,0,
            0,0,0, 0,0,0,                     
            0,0,0,
            0,0,0,
            0,0,0};
        // Board(Integer[] grid, Integer playerTurn, GameStates gameState, Integer[] unplacedPieces, Integer[] livePieces)
        // 0 - player 1, 1 - player 2
        try {
            Board myBoard = new Board(boardGrid, 0, GameStates.move, new Integer[] {9,9}, new Integer[] {0,0});
		    assertEquals(myBoard.getScore(), 0.0, 0.0001);
        } catch (Exception e) {
            fail();
        }		
	}

    public void testScoreValueOne() {
        Integer[] boardGrid=new Integer[] {  
            0,0,0,
            1,1,0,
            0,0,0,
            0,0,0, 0,0,0,                     
            0,2,0,
            0,0,0,
            0,0,0};
        // Board(Integer[] grid, Integer playerTurn, GameStates gameState, Integer[] unplacedPieces, Integer[] livePieces)
        try {
            Board myBoard = new Board(boardGrid, 0, GameStates.move, new Integer[] {7,8}, new Integer[] {2,1});
		    assertEquals(myBoard.getScore(), 1.0, 0.01);
        } catch (Exception e) {
            fail();
        }		
	}

    public void testScoreValueOneReverse() {
        Integer[] boardGrid=new Integer[] {  
            0,0,0,
            1,1,0,
            0,0,0,
            0,0,0, 0,0,0,                     
            0,2,0,
            0,0,0,
            0,0,0};
        // Board(Integer[] grid, Integer playerTurn, GameStates gameState, Integer[] unplacedPieces, Integer[] livePieces)
        try {
            Board myBoard = new Board(boardGrid, 1, GameStates.move, new Integer[] {7,8}, new Integer[] {2,1});
		    assertEquals(myBoard.getScore(), -1.0, 0.01);
        } catch (Exception e) {
            fail();
        }		
	}
    

    public void testScoreLateStage() {
        try {
            Board myBoard = new Board();
            // Have moves to get a player down to 3 pieces, then check flying
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

		    assertEquals(myBoard.getScore(), -4.0, 0.01);
        } catch (Exception e) {
            fail();
        }		
	}
    public void testScoreLateStageReverse() {
        try {
            Board myBoard = new Board();
            // Have moves to get a player down to 3 pieces, then check flying
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
			
		    assertEquals(myBoard.getScore(), -2.0, 0.01);
        } catch (Exception e) {
            fail();
        }		
	}     
    
}