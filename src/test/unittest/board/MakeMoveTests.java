package test.unittest.board;

import junit.framework.TestCase;
import main.game.*;

public class MakeMoveTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception {
		super.setUp();
		myBoard = new Board();
	}
}