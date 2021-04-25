package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class PlayersTurnTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
	
	public void testInitialState() {
		// want to have "move" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == GameStates.move);
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
		assertTrue(myBoard.GetGameState() == GameStates.move);
	}

	public void testOneRoundState() {
		// make a move
		try {
			myBoard.MakeMove(0, 0);
			myBoard.MakeMove(1, 1);
		} catch (Exception e) {
			fail();
		}
		// want to have "move" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == GameStates.move);
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
		assertTrue(myBoard.GetGameState() == GameStates.remove);
	}
	public void testOneRoundStateNewMove() {
		// make a move
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 1));
		} catch (Exception e) {
			fail();
		}
		// want to have "move" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == GameStates.move);
	}
	
	public void testRemoveStateNewMove() {
		// form a mill for player 1 
		try {
			myBoard.MakeMove(new Move(0, 0));
			myBoard.MakeMove(new Move(1, 9));
			myBoard.MakeMove(new Move(0, 1));
			myBoard.MakeMove(new Move(1, 20));
			myBoard.MakeMove(new Move(0, 2));
		} catch (Exception e) {
			fail();
		}
		// want to have "remove" game state and is player 1's turn
		assertTrue(myBoard.IsPlayersTurn(0));
		assertTrue(myBoard.GetGameState() == GameStates.remove);
	}



}
