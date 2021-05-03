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
	// frequently fails, places piece randomly
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
		
		Move nextMove = cpu.GetMove(myBoard);
		Move goalMove=new Move(0,2);
		System.out.println("Simple Placement Test:");
		System.out.println(nextMove);
		
		assertTrue(goalMove.equals(nextMove));
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
	public void testGetBestMoveFullMove() throws Exception{
		CPUOpponent cpu=new CPUOpponent();
		
		Integer[] grid=new Integer[] {1,2,1,
										2,1,0,
										1,2,0,
										1,2,0,2,1,2,
										0,0,1,
										1,1,2,
										2,2,0,};
		
		Integer[] unplacedPieces=new Integer[] {0,0};
		Integer[] livePieces=new Integer[] {9,9};
		
		myBoard=new Board(grid,0,GameStates.move,unplacedPieces,livePieces);
		
		Move nextMove=cpu.GetMove(myBoard);
		
		System.out.println("Full Move Test:");
		System.out.println(nextMove);
		assertTrue(nextMove!=null);
		
	}
	
	public void testMakeMillMovement() throws Exception {
		CPUOpponent cpu=new CPUOpponent();
		
		Integer[] grid=new Integer[] {1,1,2,
										0,1,0,
										1,2,0,
										2,1,1,0,2,0,
										1,2,0,
										1,0,0,
										1,2,2};
		
		Integer[] unplacedPieces=new Integer[] {0,00};
		Integer[] livePieces=new Integer[] {9,9};
		
		myBoard=new Board(grid,1,GameStates.move,unplacedPieces,livePieces);
		
		Move nextMove=cpu.GetMove(myBoard);
		Move goalMove = new Move(1, 14, 13);
		
		System.out.println("Make Mill Test:");
		System.out.print("Next Move: ");
		System.out.println(nextMove);
		
		assertTrue(nextMove.equals(goalMove));
	}
	
	// occasionally picks a different seemingly random move
	public void testBlockMillMovement() throws Exception {
		CPUOpponent cpu=new CPUOpponent();
		
		Integer[] grid=new Integer[] {2,0,2,
										1,0,2,
										1,0,0,
										1,0,2,0,1,2,
										1,0,2,
										1,2,2,
										1,1,2};
		
		Integer[] unplacedPieces=new Integer[] {0,0};
		Integer[] livePieces=new Integer[] {9,9};
		
		myBoard=new Board(grid,1,GameStates.move,unplacedPieces,livePieces);
		
		Move nextMove=cpu.GetMove(myBoard);
		Move goalMove = new Move(1, 10, 11);
		
		System.out.println("Block Mill Test:");
		System.out.print("Next Move: ");
		System.out.println(nextMove);


		assertTrue(nextMove.equals(goalMove));
	}
	
}
