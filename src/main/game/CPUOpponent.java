package main.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CPUOpponent { 
    
	private class moveValue implements Comparable<moveValue> {
        public Move move;
        public int score;
        public Board possBoard;

        public moveValue(Move m, int s, Board b) {
            move = m;
            score = s;
            possBoard = b;
        }

		@Override
		public int compareTo(moveValue other) {
			if (this.score > other.score) {
				return this.score - other.score;
			}
			else if (this.score == other.score) {
				return 0;
			}
			return -1;
		}

		public boolean FormsMill() {
			// check if a movement forms a mill for us
			if (possBoard.GetGameState() == GameStates.remove) {
				return false;
			} else if (possBoard.IsMill(move.getLocationTo())) {
				return true;
			} else {
				return false;
			}
		}       
    }


    private moveValue TakeTurn(Board root, Move m) {
    	
        Board childBoard = new Board(root);
        childBoard.TakeAction(m);
        ArrayList<Move> possibleMoves = childBoard.GetPossibleMoves();

        // if we formed a mill, we need to expand our moves
        // call this function on all possible moves      
        if (childBoard.GetGameState() == GameStates.remove) {
            // formed a mill, we need to consider more possibilities
            ArrayList<moveValue> fullMoves = new ArrayList<moveValue>();
            for (Move new_m: possibleMoves) {
                fullMoves.add(TakeTurn(childBoard, new_m));
            }
            moveValue bestFullMove= getBestMove(fullMoves);
            return new moveValue(m,bestFullMove.score,childBoard);
        }

        // consider the adversary 
        ArrayList<moveValue> subEvalMoves=new ArrayList<moveValue>();
        ArrayList<moveValue> fullSubEvalMoves=new ArrayList<moveValue>();;
        for (Move new_m: possibleMoves) {
            Board subchildBoard = new Board(childBoard);
            subchildBoard.TakeAction(new_m);  
            Board finalSubchildBoard;

            // did they form a mill?
            if (subchildBoard.GetGameState() == GameStates.remove) {
                // find worst case senario for ranking
                ArrayList<Move> newPossibleMoves = subchildBoard.GetPossibleMoves();
                ArrayList<moveValue> tempEvalMoves = new ArrayList<moveValue>();
                for (Move new_m_removal: newPossibleMoves) {
                    Board subSubchildBoard = new Board(subchildBoard);
                    subSubchildBoard.TakeAction(new_m_removal); 
                    int rootScore = subSubchildBoard.getScore();
                    subEvalMoves.add(new moveValue(m, rootScore, subSubchildBoard));
                }                
            }
            else {
                int rootScore = subchildBoard.getScore(); //TODO: make Board.getScore()
                subEvalMoves.add(new moveValue(m, rootScore, subchildBoard));
            }            
        }   
        
        // find lowest board score for this move (worst-case senario based on opp.)
        moveValue worstCase = getBestMove(subEvalMoves);
        return worstCase;
    }


    private ArrayList<moveValue> getChildren(Board root){
        /* calls Board.GetPossibleMoves() and receives a list of all possible moves that could be made by the current board position */
        ArrayList<Move> possibleMoves = root.GetPossibleMoves();

        /* for each move: 
            make a copy of the board object
            make move
            find possible moves of this board
            for each new move:
                make a copy of the board
                make new move
                find heuristic of each board 
            output move, heuristic, and board of lowest heuristic score */
                
        ArrayList<moveValue> evalMoves = new ArrayList<moveValue>();       
        for(Move m: possibleMoves){            
            // find highest conservative board score for this move (worst-case senario based on opp.)
            moveValue bestWorstCase = TakeTurn(root, m);
            evalMoves.add(bestWorstCase);
        }
        return evalMoves;        
    }


    private moveValue getWorstMove(ArrayList<moveValue> possibleMoveValues){
        
        moveValue out = possibleMoveValues.get(0);
        ArrayList<moveValue> selected = new ArrayList<moveValue>();
        for (moveValue mV:possibleMoveValues){
            if(mV.score < out.score) {
                out = mV;
                selected.clear();
                selected.add(mV);
            }
            else if(mV.score == out.score) {
                selected.add(mV);
            }
        }
        if (selected.size() > 1) {
            // ToDo: Tiebreaker?
            Random rand = new Random();
            return selected.get(rand.nextInt(selected.size()));
        }
        else {
            return selected.get(0);
        }
    }


    private moveValue getBestMove(ArrayList<moveValue> possibleMoveValues){

        moveValue out = possibleMoveValues.get(0);
        ArrayList<moveValue> selected = new ArrayList<moveValue>();
        for (moveValue mV:possibleMoveValues){
            if(mV.score > out.score) {
                out = mV;
                selected.clear();
                selected.add(mV);
            }
            else if(mV.score == out.score) {
                selected.add(mV);
            }
        }
        if (selected.size() > 1) {
            // ToDo: Tiebreaker?
            Random rand = new Random(System.currentTimeMillis());
            return selected.get(rand.nextInt(selected.size()));
        }
        else {
            return selected.get(0);
        }

    }

    
    public Move GetMove(Board newBoard) {
        /* take current game board and return computer opponent's move */
        ArrayList<moveValue> firstTurn = getChildren(newBoard);
        
        // normal execution to find the best move
        for(moveValue mV:firstTurn){
            // get children of all of our first moves
            // return best move in general
            ArrayList<moveValue> secondTurn = getChildren(mV.possBoard);
            if(!secondTurn.isEmpty()) {
            	moveValue bestSecondMove = getBestMove(secondTurn);
            	mV.score = bestSecondMove.score;
            }            
        }
        Collections.sort(firstTurn);
        
        // for better end game performance, we use a mill-first metric
        if ((newBoard.NumLivePieces(0) + newBoard.NumUnplacedPieces(0) < 5) || (newBoard.NumLivePieces(1) + newBoard.NumUnplacedPieces(1) < 5)) {
        	// evaluate moves for a mill formation
        	for (int i = 0; i < firstTurn.size(); i++) {
        		if (firstTurn.get(i).FormsMill()) {
        			return firstTurn.get(i).move;
        		}
        	}
        }
        return firstTurn.get(0).move;
    }
}
