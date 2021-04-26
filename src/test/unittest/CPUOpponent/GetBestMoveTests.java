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
		
		
		assertTrue(goalMove.equals(cpu.GetMove(myBoard)));
	}
	public void testGetBestMoveRemoval() throws Exception {
		CPUOpponent cpu=new CPUOpponent();
		
		Integer[] grid=new Integer[] {1,1,1,
										0,0,0,
										0,0,0,
										0,0,0,0,0,0,
										0,0,0,
										2,0,0,
										2,0,0,};
		
		Integer[] unplacedPieces=new Integer[] {6,7};
		Integer[] livePieces=new Integer[] {3,2};
		
		myBoard=new Board(grid,0,GameStates.remove,unplacedPieces,livePieces);
		
		Move nextMove=cpu.GetMove(myBoard);
		
		
		Move goalMove1=new Move(0,18,true);//removal of loc 18
		Move goalMove2=new Move(0,21,true);//removal of loc 21
		
		assertTrue(nextMove.equals(goalMove1)||nextMove.equals(goalMove2));
		
		
	}
	
}
