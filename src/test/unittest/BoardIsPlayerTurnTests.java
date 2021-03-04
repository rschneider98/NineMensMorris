package test.unittest;

import junit.framework.TestCase;
import main.java.*;

public class BoardIsPlayerTurnTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	public void testInitialState() {
		// want to have "move" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == main.java.GameStates.move);
	}

	public void testOneMoveState() {
		// make a move
		try {
			myBoard.MakeMove(0, 0);
		} catch (Exception e) {
			fail();
		}
		// want to have "move" game state and is player 2's turn
		assertTrue(myBoard.IsPlayersTurn(1));
		assertTrue(myBoard.GetGameState() == main.java.GameStates.move);
	}
	
	public void testRemoveState() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 9);
			myBoard.MakeMove(0, 1);
			myBoard.MakeMove(1, 20);
			myBoard.MakeMove(0, 2);
		} catch (Exception e) {
			fail();
		}
		// want to have "remove" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == main.java.GameStates.remove);
	}
}
