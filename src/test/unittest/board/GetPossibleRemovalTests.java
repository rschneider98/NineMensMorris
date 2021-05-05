package test.unittest.board;

import java.util.ArrayList;

import junit.framework.TestCase;
import main.game.*;

public class GetPossibleRemovalTests extends TestCase {
	public void testInitialState() {
        // is placement, and no mill is formed, so it should be zero
		Board myBoard=new Board();
        ArrayList<Move> PossibleRemovals=myBoard.getPossibleRemovals();
		assertEquals(PossibleRemovals.size(), 0);        
	}

    public void testRemovalPlayerOne() throws Exception {
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
    
    public void testRemovalPlayerTwo() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 23);	// Makes Mill

                        
            ArrayList<Move> PossibleRemovals = myBoard.getPossibleRemovals();
            assertEquals(PossibleRemovals.size(), 3);
            
            boolean hasRemoval0 = false;
            boolean hasRemoval9 = false;
            boolean hasRemoval3 = false;
            
            for (Move mV: PossibleRemovals) {
            	assertTrue(mV.isRemove());
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	if (mV.getLocationTo() == 0) {
            		hasRemoval0 = true;
            	}
            	else if (mV.getLocationTo() == 9) {
            		hasRemoval9 = true;
            	}
            	else if (mV.getLocationTo() == 3) {
            		hasRemoval3 = true;
            	}
            }
            
            assertTrue(hasRemoval0 && hasRemoval9 && hasRemoval3);

        } catch (Exception e) {
            fail();
        }
	}
    
    
    public void testRemovalWithMillPlayer1() throws Exception {
        // is placement, so any open spot should be valid
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 21);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 23);	// Makes Mill
            myBoard.RemoveMan(1, 10);	// Removes piece
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 9);	// Makes Mill

                        
            ArrayList<Move> PossibleRemovals = myBoard.getPossibleRemovals();
            assertEquals(PossibleRemovals.size(), 1);
            
            for (Move mV: PossibleRemovals) {
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	assertTrue(mV.isRemove());
            	assertEquals(mV.getLocationTo(), 13);
            }

        } catch (Exception e) {
            fail();
        }
	}
    
    public void testRemovalWithMillPlayer2() throws Exception {
        // is placement, so any open spot should be valid
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 21);	// Makes mill
            myBoard.RemoveMan(0, 13);
            myBoard.MakeMove(1, 23);	// Makes mill
                        
            ArrayList<Move> PossibleRemovals = myBoard.getPossibleRemovals();
            assertEquals(PossibleRemovals.size(), 1);
            
            for (Move mV: PossibleRemovals) {
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	assertTrue(mV.isRemove());
            	assertEquals(mV.getLocationTo(), 10);
            }

        } catch (Exception e) {
            fail();
        }
	}
    
    public void testRemovalWithOnlyMillsPlayerOne() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 23);	// Makes mill
            myBoard.RemoveMan(1, 10);
            
            myBoard.MakeMove(0, 21);	// Makes mill
                        
            ArrayList<Move> PossibleRemovals = myBoard.getPossibleRemovals();
            assertEquals(PossibleRemovals.size(), 3);
            
            boolean hasRemoval2 = false;
            boolean hasRemoval14 = false;
            boolean hasRemoval23 = false;
            
            for (Move mV: PossibleRemovals) {
            	if (mV.isRemove()) {
            	}
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	if (mV.getLocationTo() == 2) {
            		hasRemoval2 = true;
            	}
            	else if (mV.getLocationTo() == 14) {
            		hasRemoval14 = true;
            	}
            	else if (mV.getLocationTo() == 23) {
            		hasRemoval23 = true;
            	}
            }
            
            assertTrue(hasRemoval2 && hasRemoval14 && hasRemoval23);
            
        } catch (Exception e) {
            fail();
        }
	}
    
    public void testRemovalWithOnlyMillsPlayerTwo() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 21);	// Makes mill
            myBoard.RemoveMan(0, 14);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 11);	// Makes mill
            myBoard.RemoveMan(0, 13);
            myBoard.MakeMove(1, 23);	// Makes mill
                        
            ArrayList<Move> PossibleRemovals = myBoard.getPossibleRemovals();
            assertEquals(PossibleRemovals.size(), 5);
            ArrayList<Integer> oppLocs = myBoard.getPlayerLocs(0);
            
            for (Move mV: PossibleRemovals) {
            	assertTrue(mV.isRemove());
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	boolean hasLoc = false;
            	for (Integer loc: oppLocs) {
            		if (mV.getLocationTo() == loc) {
            			hasLoc = true;
            		}
            	}
            	assertTrue(hasLoc);
            }
        } catch (Exception e) {
            fail();
        }
	}
    
}
