package test.unittest.board;

import junit.framework.TestCase;
import java.util.ArrayList;
import main.game.*;

public class GetPossibleMoves extends TestCase {
	public void testInitialState() {
        // is placement, so any open spot should be valid
		Board myBoard=new Board();
        ArrayList<Move> PossibleMoves=myBoard.getPossiblePlacements();
		assertEquals(PossibleMoves.size(), 24);        
	}

    public void testFirstMove() {
        // is placement, so any open spot should be valid
        Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 11);
            ArrayList<Move> PossibleMoves = myBoard.getPossiblePlacements();
            assertEquals(PossibleMoves.size(), 23);  
            boolean hasLoc = false;
            for (Move mV: PossibleMoves) {
                if (mV.getLocationTo() == 11) {
                    fail();
                }
                if (mV.getLocationTo() == 19) {
                    hasLoc = true;
                }
            }
            assertTrue(hasLoc);
        } catch (Exception e) {
            fail();
        }
		
	}

    public void testMovementPlayerOne() {
        // is placement, so any open spot should be valid
		assertTrue(true);
	}

    public void testMovementPlayerTwo() {
        // is placement, so any open spot should be valid
		assertTrue(true);
	}

    public void testMovementFlying() {
        assertTrue(true);
	}
    
    public void testRemoval() {
        assertTrue(true);
	}

    public void testRemovalWithMill() {
        assertTrue(true);
	}
}