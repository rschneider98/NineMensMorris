package main.game;
import java.util.ArrayList;
import java.util.Random;

public class CPUOpponent { 
    
	private class moveValue {
        public Move move;
        public int score;
        public Board possBoard;

        public moveValue(Move m, int s, Board b) {
            move = m;
            score = s;
            possBoard = b;
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
        moveValue worstCase = getWorstMove(subEvalMoves);
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
                find heureistic of each board 
            output move, hueristic, and board of lowest heuristic score */
        

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
        
        for(moveValue mV:firstTurn){
            // get children of all of our first moves
            // return best move in general
            ArrayList<moveValue> secondTurn = getChildren(mV.possBoard);
            if(!secondTurn.isEmpty()) {
            	moveValue bestSecondMove = getBestMove(secondTurn);
            	mV.score = bestSecondMove.score;
            }
            
        }
        moveValue bestMove = getBestMove(firstTurn);
        return bestMove.move;
    }
}
