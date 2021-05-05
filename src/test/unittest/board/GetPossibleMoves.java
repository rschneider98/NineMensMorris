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
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 21);
            myBoard.MakeMove(1, 9);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 3);
            
            myBoard.MakeMove(0, 1);
            myBoard.MakeMove(1, 18);
            
            myBoard.MakeMove(0, 4);
            myBoard.MakeMove(1, 7);
            
            myBoard.MakeMove(0, 22);
            myBoard.MakeMove(1, 5);
            
            myBoard.MakeMove(0, 8);
            myBoard.MakeMove(1, 12);
            
            myBoard.MakeMove(0, 20);
            myBoard.MakeMove(1, 19);
            
            myBoard.MakeMove(0, 13);
            myBoard.MakeMove(1, 14);
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 2);
            
            boolean hasMove1 = false;
            boolean hasMove2 = false;
            for (Move mV: PossibleMoves) {
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	if (mV.getLocationFrom() != 10 && mV.getLocationFrom() != 22) {
            		fail();
            	}
            	if (mV.getLocationFrom() == 10 && mV.getLocationTo() == 11) {
            		hasMove1 = true;
            	}
            	if (mV.getLocationFrom() ==22 && mV.getLocationTo() == 23) {
            		hasMove2 = true;
                }
            }
            assertTrue(hasMove1);
            assertTrue(hasMove2);
        } catch (Exception e) {
            fail();
        }
	}

    public void testMovementPlayerTwo() {
        // is placement, so any open spot should be valid
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 21);
            myBoard.MakeMove(1, 9);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 3);
            
            myBoard.MakeMove(0, 1);
            myBoard.MakeMove(1, 18);
            
            myBoard.MakeMove(0, 4);
            myBoard.MakeMove(1, 7);
            
            myBoard.MakeMove(0, 22);
            myBoard.MakeMove(1, 5);
            
            myBoard.MakeMove(0, 8);
            myBoard.MakeMove(1, 12);
            
            myBoard.MakeMove(0, 20);
            myBoard.MakeMove(1, 19);
            
            myBoard.MakeMove(0, 13);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 23, 22);
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 4);
            
            boolean hasMove1 = false;
            boolean hasMove2 = false;
            boolean hasMove3 = false;
            boolean hasMove4 = false;
            for (Move mV: PossibleMoves) {
            	
            	if (mV.getLocationTo() < 0 || mV.getLocationTo() > 23) {
        			fail();
        		}
            	
            	if (mV.getLocationFrom() != 7 && mV.getLocationFrom() != 12
            			&& mV.getLocationFrom() != 19) {
            		fail();
            	}
            	if (mV.getLocationFrom() == 7 && mV.getLocationTo() == 6) {
            		hasMove1 = true;
            	}
            	if (mV.getLocationFrom() == 12 && mV.getLocationTo() == 17) {
            		hasMove2 = true;
                }
            	if (mV.getLocationFrom() == 19) {
            		if (mV.getLocationTo() == 16) {
            			hasMove3 = true;
            		}
            		else if (mV.getLocationTo() == 22) {
            			hasMove4 = true;
            		}
            	}
            }
            assertTrue(hasMove1);
            assertTrue(hasMove2);
            assertTrue(hasMove3);
            assertTrue(hasMove4);
        } catch (Exception e) {
            fail();
        }
	}
    
    public void testMovementFlyingPlayerOne() {
        // is placement, so any open spot should be valid
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 23);
            myBoard.RemoveMan(1, 3);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 5);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 6);
            myBoard.MakeMove(1, 20);
            myBoard.RemoveMan(1, 6);
            
            myBoard.MakeMove(0, 6);
            myBoard.MakeMove(1, 12);
            myBoard.RemoveMan(1, 6);
            
            myBoard.MakeMove(0, 6);
            myBoard.MakeMove(1, 8);
            
            myBoard.MakeMove(0, 15);
            myBoard.MakeMove(1, 17);
            myBoard.RemoveMan(1, 15);
            
            myBoard.MakeMove(0, 1, 0);
            myBoard.MakeMove(1, 16, 17);
            
            myBoard.MakeMove(0, 0, 1);
            myBoard.MakeMove(1, 17, 16);
            myBoard.RemoveMan(1, 6);
            
            myBoard.MakeMove(0, 1, 0);
            myBoard.MakeMove(1, 16, 17);
            
            myBoard.MakeMove(0, 0, 1);
            myBoard.MakeMove(1, 17, 16);
            myBoard.RemoveMan(1, 10);
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 36);
            
            ArrayList<Integer> takenLocs = new ArrayList<Integer>();
            takenLocs.addAll(myBoard.getPlayerLocs(0));
            takenLocs.addAll(myBoard.getPlayerLocs(1));
            
            for (Move move: PossibleMoves) {
            	if (move.getLocationTo() < 0 || move.getLocationTo() > 23) {
        			fail();
        		}
            	for (Integer loc: takenLocs) {
            		if (move.getLocationTo() == loc) {
            			fail();
            		}
            	}
            }
        } catch (Exception e) {
            fail();
        }
	}

    public void testMovementFlyingPlayerTwo() {
        // is placement, so any open spot should be valid
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 21);
            myBoard.RemoveMan(0, 14);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 5);
            
            myBoard.MakeMove(0, 11);
            myBoard.RemoveMan(0, 14);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 18);
            myBoard.RemoveMan(0, 13);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 15);
            myBoard.MakeMove(1, 8);
            
            myBoard.MakeMove(0, 6);
            myBoard.RemoveMan(0, 8);
            myBoard.MakeMove(1, 8);
            
            myBoard.MakeMove(0, 4, 3);
            myBoard.MakeMove(1, 23, 14);
            
            myBoard.MakeMove(0, 3, 4);
            myBoard.RemoveMan(0, 23);
            myBoard.MakeMove(1, 14, 13);
            
            myBoard.MakeMove(0, 4, 3);
            myBoard.MakeMove(1, 12, 8);
            
            myBoard.MakeMove(0, 3, 4);
            myBoard.RemoveMan(0, 12);
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 36);
            
            ArrayList<Integer> takenLocs = new ArrayList<Integer>();
            takenLocs.addAll(myBoard.getPlayerLocs(0));
            takenLocs.addAll(myBoard.getPlayerLocs(1));
            
            for (Move move: PossibleMoves) {
            	if (move.getLocationTo() < 0 || move.getLocationTo() > 23) {
        			fail();
        		}
            	for (Integer loc: takenLocs) {
            		if (move.getLocationTo() == loc) {
            			fail();
            		}
            	}
            }
        } catch (Exception e) {
            fail();
        }
	}
    
    public void testRemovalPlayerOne() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 21);	// Makes mill

                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 2);
            
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	if (move.getLocationTo() != 2 && move.getLocationTo() != 14) {
            		fail();
            	}
            }
        } catch (Exception e) {
            fail();
        }
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

                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 3);
            
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	if (move.getLocationTo() != 0 && move.getLocationTo() != 3 
            			&& move.getLocationTo() != 9) {
            		fail();
            	}
            }
        } catch (Exception e) {
            fail();
        }
	}

    public void testRemovalWithMillPlayerOne() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 10);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 23);	// Makes mill
            myBoard.RemoveMan(1, 3);
            
            myBoard.MakeMove(0, 21);	// Makes Mill

                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 1);
            
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	assertEquals(move.getLocationTo(), 13);
            }
        } catch (Exception e) {
            fail();
        }
	}
    
    public void testRemovalWithMillPlayerTwo() {
    	Board myBoard=new Board();
        try {
            myBoard.MakeMove(0, 0);
            myBoard.MakeMove(1, 2);
            
            myBoard.MakeMove(0, 9);
            myBoard.MakeMove(1, 14);
            
            myBoard.MakeMove(0, 3);
            myBoard.MakeMove(1, 13);
            
            myBoard.MakeMove(0, 21);	// Makes mill
            myBoard.RemoveMan(0, 13);
            myBoard.MakeMove(1, 23);	// Makes mill
            
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 1);
            
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	assertEquals(move.getLocationTo(), 3);
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
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 3);
            
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	if (move.getLocationTo() != 2 && move.getLocationTo() != 14 
            			&& move.getLocationTo() != 23) {
            		fail();
            	}
            }
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
                        
            ArrayList<Move> PossibleMoves = myBoard.GetPossibleMoves();
            assertEquals(PossibleMoves.size(), 5);
            
            ArrayList<Integer> oppLocs = myBoard.getPlayerLocs(0);
            for (Move move: PossibleMoves) {
            	assertTrue(move.isRemove());
            	boolean hasLoc = false;
            	for (Integer loc: oppLocs) {
            		if (move.getLocationTo() == loc) {
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