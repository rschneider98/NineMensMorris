package test.unittest.board;

import java.util.ArrayList;

import junit.framework.TestCase;
import main.game.Board;
import main.game.GameStates;
import main.game.Move;

public class GetPossibleRemovalTests extends TestCase {
	public void testInitialState() {
        // is placement, and no mill is formed, so it should be zero
		Board myBoard=new Board();
        ArrayList<Move> PossibleRemovals=myBoard.getPossibleRemovals();
		assertEquals(PossibleRemovals.size(), 0);        
	}

    public void testFirstMove() throws Exception {
        // is placement, so any open spot should be valid
    	Integer[] grid=new Integer[] {1,1,1,
				0,0,0,
				0,0,0,
				0,0,0,0,0,0,
				0,0,0,
				2,0,0,
				2,0,0,};

		Integer[] unplacedPieces=new Integer[] {6,7};
		Integer[] livePieces=new Integer[] {3,2};
		
		Board myBoard=new Board(grid,0,GameStates.remove,unplacedPieces,livePieces);
		
		ArrayList<Move> possibleRemovals=myBoard.getPossibleRemovals();
		
		assertTrue(possibleRemovals.size()==2);
		
		boolean foundMove1=false;
		boolean foundMove2=false;
		
		for(Move mv:possibleRemovals) {
			
			if(mv.getLocationTo()==18) {
				foundMove1=true;
			}
			if(mv.getLocationTo()==21) {
				foundMove2=true;
			}
		}
		
		assertTrue(foundMove1&&foundMove2);
		
	}

    
}
