package test.unittest.CPUOpponent;

import junit.framework.TestCase;
import main.game.Board;
import main.game.CPUOpponent;
import main.game.GameStates;
import main.game.Move;

public class GetBestMoveTests extends TestCase {
	private Board myBoard;
	
	protected void setUp() throws Exception{
		super.setUp();
		
	}
	public void testGetBestMoveSimplePlacement() throws Exception {
		CPUOpponent cpu=new CPUOpponent();
		
		Integer[] grid=new Integer[] {1,1,0,
										0,0,0,
										0,0,0,
										0,0,0,0,0,0,
										0,0,0,
										2,0,0,
										2,0,0,};
		
		Integer[] unplacedPieces=new Integer[] {7,7};
		Integer[] livePieces=new Integer[] {2,2};
		
		myBoard=new Board(grid,0,GameStates.move,unplacedPieces,livePieces);
		
		Move goalMove=new Move(0,2);
		System.out.println(goalMove.toString());
		System.out.println(cpu.GetMove(myBoard));
		
		assertTrue(goalMove.equals(cpu.GetMove(myBoard)));
	}
	
}
