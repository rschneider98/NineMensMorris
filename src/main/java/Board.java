package main.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Board {
	private String playerOneName;
	private String playerTwoName;
	
	// board is stored as array of length 24
	// see docs for enumeration of positions
	private Integer[] boardLoc = new int[24];
	// whose turn it is, 0 - player 1; 1 - player 2
	private Integer playerTurn = 0;
	// map for adjacency list of locations
	private HashMap<Integer, Integer[]> adj = new HashMap<Integer, Integer[]>();
	private GameStates gameState;
	
	// pieces for players
	private Integer[] unplacedPieces = new Integer[] {9, 9};
	private Integer[] livePieces = new Integer[] {9, 9};
	
	public Board(String p1, String p2) {
		// mark all spots on the board as empty
		Arrays.fill(boardLoc, 0, 23, 0);
		
		// adjacency list for each of the board locations
		adj.put(0, new Integer[] {1, 9});
		adj.put(1, new Integer[] {0, 2, 4});
		adj.put(2, new Integer[] {1, 14});
		adj.put(3, new Integer[] {4 10});
		adj.put(4, new Integer[] {1, 3, 5, 7});
		adj.put(5, new Integer[] {4, 13});
		adj.put(6, new Integer[] {7, 11});
		adj.put(7, new Integer[] {4, 6, 8});
		adj.put(8, new Integer[] {7, 12});
		adj.put(9, new Integer[] {0, 10, 21});
		adj.put(10, new Integer[] {3, 9, 11, 18});
		adj.put(11, new Integer[] {6, 10, 15});
		adj.put(12, new Integer[] {8, 13, 17});
		adj.put(13, new Integer[] {5, 12, 14, 20});
		adj.put(14, new Integer[] {2, 13, 23});
		adj.put(15, new Integer[] {11, 16});
		adj.put(16, new Integer[] {15, 17, 19});
		adj.put(17, new Integer[] {12, 16});
		adj.put(18, new Integer[] {10, 19});
		adj.put(19, new Integer[] {16, 18, 20, 22});
		adj.put(20, new Integer[] {13, 19});
		adj.put(21, new Integer[] {9, 22});
		adj.put(22, new Integer[] {19, 21, 23});
		adj.put(23, new Integer[] {14, 22});
		
		// mark initial game state
		gameState = GameStates.move;
		
		// set player names
		playerOneName = p1;
		playerTwoName = p2;
	}
	
	// setters and getters for dealing with the board
	private void PlacePiece(Integer playerNum, Integer location) {
		// places a piece at a location and decrements unplaced count
		unplacedPieces[playerNum]--;
		boardLoc[location] = playerNum;
	}
	private void MovePiece(Integer playerNum, Integer locationTo, Integer locationFrom) {
		// moves a pieces from one location to another
		boardLoc[locationTo] = playerNum;
		boardLoc[locationFrom] = 0;
	}
	private void RemovePiece(Integer location) {
		// removes a piece at location
		Integer player = boardLoc[location] - 1;
		livePieces[player]--;
		boardLoc[location] = 0;		
	}
	private boolean IsPlayersTurn(Integer playerNum) {
		// checks if it is a player's turn
		return (playerTurn == playerNum);
	}
	private boolean IsValidLoc(Integer location) {
		// checks if the location is valid for our data structure
		return !(location < 0 || location > 23);
	}
	private boolean IsEmpty(Integer location) {
		// checks if location is empty
		return (boardLoc[location] == 0);
	}
	private boolean IsPlayersPiece(Integer playerNum, Integer location) {
		// checks if the location's piece is the player's
		return (boardLoc[location] == (playerNum + 1));
	}
	private boolean HasUnplacedPieces(Integer playerNum) {
		// checks if a player has unplaced pieces
		return (unplacedPieces[playerNum] > 0);
	}
	private boolean AreAdjacent(Integer loc1, Integer loc2) {
		// checks if the location are adjacent
		return (Arrays.asList(adj.get(loc1)).contains(loc2));
	}
	private boolean CanFly() {
		// checks players can fly pieces
		return (livePieces[0] <= 3 || livePieces[1] <= 3);
	}
	private boolean IsMill(Integer location) {
		// checks if the location is part of a mill
		Integer numAdj = 0;
		// get the value of the location
		Integer playerNum = boardLoc[location];
		// trival case - is empty
		if (playerNum == 0) {
			return false;
		}
		numAdj++; // increment count of adjacent men
		// get array of adjacent locations
		Integer[] possibleMen1 = adj.get(location);
		// create vector to store locations with same piece
		Vector<Integer> possibleMen2 = new Vector<Integer>();
		// for each adjacent location see if is the same piece
		// then increment the counter and add this to the second round 
		for (int i = 0; i < possibleMen1.length(); i++) {
			if (boardLoc[possibleMen1[i]] == playerNum) {
				adj++;
				possibleMen2.add(possibleMen1[i]);
			}
		}
		// second round occurs if we have an adjacent piece 
		// and there is not yet a mill
		// check each of these candidates for a 
		while (adj < 3 && possibleMen2.size() > 0) {
			adjLoc = possibleMen2.remove(0);
			Integer[] possibleMen = adj.get(adjLoc);
			for (int i = 0; i < possibleMen.length(); i++) {
				if (boardLoc[possibleMen[i]] == playerNum) {
					adj++;
				}
			}
		}
		return (adj >= 3);		
	}
	private boolean HasLegalMoves(Integer playerNum) {
		// checks if a player has a possible move
		// if looking for movement
		if (gameState == GameState.move) {
			// if they can fly, there is a garenteed move
			if (CanFly()) {
				return true;
			}
			// otherwise start with loc 0 
			// see if the player is in that position
			// and there are open adjacent spot
			int i = 0;
			while (i < 24) {
				if (boardLoc[i] == playerNum) {
					Integer possibleAdj = adj.get(i);
					for (int j = 0; j < possibleAdj.length(); j++) {
						if (IsEmpty(possibleAdj[j])) {
							return true;
						}
					}
				}
				i++;
			}
			// if we iterated through the full board and they cannot move
			return false;
		}
		// want to find a piece of the opponent that is not in a mill
		if (gameState == GameState.remove) {
			int i = 0;
			while (i < 24) {
				if (boardLoc[i] == ((playerNum + 1) % 2) {
					if (!IsMill(i)) {
						return true;
					}
				}
				i++;
			}
			return false;
		}
		// if neither of those game states (impossible since enum)
		return false;
	}
	public GameState GetGameState() {
		// gets the game state on board
		return gameState;
	}
	public boolean IsEnd() {
		// sees if it is the end of the game
		// if a player has two pieces
		if (livePieces[0] <= 2 || livePieces[1] <= 2) {
			return true;
		}
		// check if either player has legal moves
		if (!HasLegalMoves(0) || !HasLegalMoves(1)) {
			return true;
		}
		return false;
	}
	
	
	// public checks for valid moves and end game
	public boolean IsValidMovement(Integer playerNum, Integer location) {
		/* Method to check if a particular movement is valid 
		 * movement with only one location is placement */
		// check that the location exists
		if (!IsValidLoc(location)) {
			return false;
		}
		// check that it is the player's turn
		if (!IsPlayerTurn(playerNum)) {
			return false;
		}
		// check that the player has piece's left
		if (!HasUnplacedPieces(playerNum)) {
			return false;
		}
		// check that the location is empty
		if (!IsEmpty(location)) {
			return false;
		}
		// check game state
		if (GetGameState() != GameState.move) {
			return false;
		}
		return true;		
	}
	public boolean IsValidMovement(Integer playerNum, Integer locationTo, Integer locationFrom) {
		/* Method to check if a particular movement is valid 
		 * movement with two locations is adj. or flying */
		// check that both locations exist
		if (!IsValidLoc(locationTo)) {
			return false;
		}
		if (!IsValidLoc(locationFrom)) {
			return false;
		}
		// check that it is the player's turn
		if (!IsPlayersTurn(playerNum)) {
			return false;
		}
		// check that the player has no piece's left
		if (HasUnplacedPieces(playerNum)) {
			return false;
		}
		// check that the location to is empty
		if (!IsEmpty(locationTo)) {
			return false;
		}
		// check that the locationFrom is the correct player
		if (!IsPlayersPiece(playerNum, locationFrom)) {
			return false;
		}		
		// check if the two locations are adjacent or if flying is valid
		// !Adj && !CanFly <==> !(Adj || CanFly)
		if (!(AreAdjacent(locationTo, locationFrom) || CanFly())) {
			return false;
		}
		// check game state
		if (GetGameState() != GameState.move) {
			return false;
		}
		return true;
	}
	public boolean IsValidRemoval(Integer playerNum, Integer location) {
		// Checks if a removal is valid
		// check that the location exists
		if (!IsValidLoc(location)) {
			return false;
		}
		// check that it is the player's turn
		if (!IsPlayerTurn(playerNum)) {
			return false;
		}
		// check that the location is nonempty
		if (IsEmpty(location)) {
			return false;
		}
		// check that the location correct player
		if (!IsPlayersPiece(((playerNum + 1) % 2), location)) {
			return false;
		}
		// check game state
		if (GetGameState() != GameState.remove) {
			return false;
		}
		// check if the location is a part of a mill
		if (IsMill(location)) {
			return false;
		}
		return true;	
	}
	
	
	// public functions for making moves
	public boolean MakeMove(Integer playerNum, Integer location) throws Exception {
		/* Make the move (with one location argument this will be placement) 
		 * and return if a mill has formed
		 * If the move is not valid, this will throw an error */
		if (!IsValidMove(playerNum, location)) {
			throw new Exception("Invalid placement");
		}
		// otherwise is valid move
		PlacePiece(playerNum, location);
		return (IsMill(location));
	}
	public boolean MakeMove(Integer playerNum, Integer locationTo, Integer locationFrom) {
		/* Make the move (with two location arguments this will be movement) 
		 * and return if a mill has formed
		 * If the move is not valid, this will throw an error */
		if (!IsValidMove(playerNum, locationTo, LocationFrom)) {
			throw new Exception("Invalid movement");
		}
		// otherwise is valid move
		MovePiece(playerNum, locationTo, locationFrom);
		return (IsMill(locationTo));
	}
	public void RemoveMan(Integer playerNum, Integer location) {
		/* Remove a piece
		 * If the move is not valid, this will throw an error */
		if (!IsValidRemoval(playerNum, location)) {
			throw new Exception("Invalid movement");
		}
		// otherwise is valid move
		RemovePiece(playerNum, location);
	}	
}