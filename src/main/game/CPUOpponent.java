package main.game;
import java.util.ArrayList;
import java.util.Random;

public class CPUOpponent { 
    private class moveValue {
        public Move move;
        public Double score;
        public Board possBoard;

        public moveValue(Move m, Double s, Board b) {
            move=m;
            score=s;
            possBoard=b;
        }

       
    }
    
    public CPUOpponent(Board currBoard){

        this.currBoard=currBoard;
        
    }
    private ArrayList<moveValue> getChildren(Board root){

        /* calls Board.GetPossibleMoves() and receives a list of all possible moves that could be made by the current board position */
        ArrayList<Move> possibleMoves = root.GetPossibleMoves(root);

        /* for each move: 
            make a copy of the board object
            make move
            find possible moves of this board
            for each new move:
                make a copy of the board
                make new move
                find heureistic of each board 
            output move, hueristic, and board of lowest heuristic score */
        

        ArrayList<moveValue> evalMoves;       

        for(Move m: possibleMoves){
            Board childBoard = new Board(root);
            childBoard.MakeMove(m);
            ArrayList<Move> newPossibleMoves = childBoard.GetPossibleMoves();
            
            ArrayList<moveValue> subEvalMoves;
            for (Move new_m: newPossibleMoves) {
                Board subchildBoard = new Board(childBoard);
                subchildBoard.MakeMove(new_m);  //TODO: override makeMove(Move currMove)
                double rootScore=subchildBoard.getScore(); //TODO: make Board.getScore()
                subEvalMoves.add(new moveValue(m, rootScore, subchildBoard));
            }   
            
            // find lowest board score for this move (worst-case senario based on opp.)
            moveValue worstCase = getWorstMove(subEvalMoves);
            evalMoves.add(worstCase);
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
            Random rand = new Random();
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
            moveValue bestSecondMove = getBestMove(secondTurn);
            mV.score = bestSecondMove.score;
        }
        moveValue bestMove = getBestMove(firstTurn);
        return bestMove.move;
    }
}
