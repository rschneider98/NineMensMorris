package main.game;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Board {
	// board is stored as array of length 24
	// see doc for enumeration of positions
	private Integer[] boardLoc = new Integer[24];
	// whose turn it is, 0 - player 1; 1 - player 2
	private Integer playerTurn = 0;
	// map for adjacency list of locations
	private HashMap<Integer, Integer[]> adj = new HashMap<Integer, Integer[]>();
	private GameStates gameState;
	
	private String playerOneName;
	private String playerTwoName;
	private String dispStatus;
	
	private int prevClick=0;
	private boolean moveInProgress;
	
	// (immutable) array of possible mills
	private static final Integer[][] possMills = {
		{0, 1, 2}, 
		{3, 4, 5},
		{6, 7, 8},
		{9, 10, 11},
		{12, 13, 14},
		{15, 16, 17},
		{18, 19, 20},
		{21, 22, 23},
		{0, 9, 21},
		{3, 10, 18},
		{6, 11, 15},
		{1, 4, 7},
		{16, 19, 22},
		{8, 12, 17},
		{5, 13, 20},
		{2, 14, 23}
	};

	// pieces for players
	private Integer[] unplacedPieces = new Integer[] {9, 9};
	private Integer[] livePieces = new Integer[] {9, 9};

	private void createAdjList() {
		// mark all spots on the board as empty
		Arrays.fill(boardLoc, 0, 24, 0);
		
		// adjacency list for each of the board locations
		adj.put(0, new Integer[] {1, 9});
		adj.put(1, new Integer[] {0, 2, 4});
		adj.put(2, new Integer[] {1, 14});
		adj.put(3, new Integer[] {4, 10});
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
	}
	
	public Board() {	
		createAdjList();
		// mark initial game state
		gameState = GameStates.move;
	}

	public Board(Board prev) {	
		createAdjList();
		// copy data
		gameState = GameStates.move;
		
		this.playerTurn = prev.playerTurn;
		this.gameState = prev.gameState;
	
		this.playerOneName = prev.playerOneName;
		this.playerTwoName = prev.playerTwoName;
	
		
		System.arraycopy(prev.boardLoc, 0, this.boardLoc, 0, prev.boardLoc.length); //creates a copy of the boardLoc array
		System.arraycopy(prev.unplacedPieces, 0, this.unplacedPieces, 0, prev.unplacedPieces.length); //creates a copy of the unplacedPieces array
		System.arraycopy(prev.livePieces, 0, this.livePieces, 0, prev.livePieces.length); //creates a copy of the livePieces array
	}

	public Board(Integer[] grid, Integer playerTurn, GameStates gameState, Integer[] unplacedPieces, Integer[] livePieces) throws Exception {	
		createAdjList();
		// validate the data
		// grid is of length 24
		if(grid.length!=24){
			throw new Exception("Wrong Length");
		}

		// playerturn is either 0 or 1
		if(playerTurn != 0 && playerTurn != 1) {
			throw new Exception("Invalid Turn");
		}

		// unplacedPieces is array of length two with elements 0 - 9 inclusive 
		if(unplacedPieces.length!=2){
			throw new Exception("Invalid unplacedPieces length");
		}
		if(!(unplacedPieces[0]>-1 && unplacedPieces[0]<10)||!(unplacedPieces[1]>-1 && unplacedPieces[1]<10)){
			throw new Exception("Invalid range of unplacedPieces");
		}
		// live pieces is array of length two with elements 0 - 9 inclusive
		if (livePieces.length != 2) {
			throw new Exception("Invalid livePieces length");
		}

		if (!(livePieces[0] > -1 && livePieces[0] < 10) || !(livePieces[1] > -1 && livePieces[1] < 10)) {
			throw new Exception("Invalid range of livePieces");
		}

		// unplaced + live pieces are within range 3 - 9
		int p1 = unplacedPieces[0] + livePieces[0];
		int p2 = unplacedPieces[1] + livePieces[1];
		if (p1 < 3 || p1 > 9) {
			throw new Exception("Cannot have more than nine (9) pieces");
		}
		if (p2 < 3 || p2 > 9) {
			throw new Exception("Cannot have more than nine (9) pieces");
		}

		// copy data
		this.gameState = gameState;
		this.boardLoc = grid;
		this.playerTurn = playerTurn;
	
		this.unplacedPieces = unplacedPieces;
		this.livePieces = livePieces;
	}

	// save/load class to file
	@SuppressWarnings("unchecked")
	public void toFile(String filename) throws IOException {
		// add data to JSON object
		JSONObject boardObject = new JSONObject();
		
		JSONArray grid = new JSONArray();
		for (int i = 0; i < 24; i++) {
			grid.add(boardLoc[i]);
		}
		
		boardObject.put("boardLoc", grid);
		boardObject.put("playerTurn", playerTurn);
		if (gameState == GameStates.move) {
			boardObject.put("gameState", "move");
		} else {
			boardObject.put("gameState", "remove");
		}

		boardObject.put("unplacedPiecesP1", unplacedPieces[0]);
		boardObject.put("unplacedPiecesP2", unplacedPieces[1]);
		boardObject.put("livePiecesP1", livePieces[0]);
		boardObject.put("livePiecesP2", livePieces[1]);
		boardObject.put("playerOneName", playerOneName);
		boardObject.put("playerTwoName", playerTwoName);
		boardObject.put("dispStatus", dispStatus);

		// write JSON object to file
		FileWriter file = new FileWriter(filename);
		file.write(boardObject.toJSONString());
		file.flush();
		file.close();
	}

	public void fromFile(String filename) throws Exception {
		// load data from file
		JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(filename);
		Object obj = jsonParser.parse(reader);

		JSONObject jsonObj = (JSONObject) obj;

		this.playerOneName = (String) jsonObj.get("playerOneName");
		this.playerTwoName = (String) jsonObj.get("playerTwoName");
		this.dispStatus = (String) jsonObj.get("dispStatus");
		
		String currState= (String) jsonObj.get("gameState");

		switch(currState){
			case "move":
				this.gameState = GameStates.move;
				break;
			case "remove":
				this.gameState = GameStates.remove;
				break;
			default:
				throw new Exception("Invalid GameState");
		}

		int livePiecesP1 =  ((Long) jsonObj.get("livePiecesP1")).intValue();
		int livePiecesP2 = ((Long) jsonObj.get("livePiecesP2")).intValue();
		Integer[] livePieces = new Integer[] {livePiecesP1, livePiecesP2};
		
		int unplacedPiecesP1 = ((Long) jsonObj.get("unplacedPiecesP1")).intValue();
		int unplacedPiecesP2 = ((Long) jsonObj.get("unplacedPiecesP2")).intValue();
		Integer[] unplacedPieces = new Integer[] {unplacedPiecesP1, unplacedPiecesP2};

		int playerTurn =  ((Long) jsonObj.get("playerTurn")).intValue();
		
		JSONArray rawGrid =(JSONArray) jsonObj.get("boardLoc");
		
		Integer[] grid= new Integer[24];
		
		for(int x = 0; x<24; x++) {
			grid[x] = ((Long) rawGrid.get(x)).intValue();
		}
		
		// validate the data
		// grid is of length 24
		if(grid.length!=24){
			throw new Exception("Wrong Length");
		}

		// playerTurn is either 0 or 1
		if(playerTurn != 0 && playerTurn != 1) {
			throw new Exception("Invalid Turn");
		}

		// unplacedPieces is array of length two with elements 0 - 9 inclusive 
		if(unplacedPieces.length!=2){
			throw new Exception("Invalid unplacedPieces length");
		}
		if(!(unplacedPieces[0]>-1 && unplacedPieces[0]<10)||!(unplacedPieces[1]>-1 && unplacedPieces[1]<10)){
			throw new Exception("Invalid range of unplacedPieces");
		}
		// live pieces is array of length two with elements 0 - 9 inclusive
		if (livePieces.length != 2) {
			throw new Exception("Invalid livePieces length");
		}

		if (!(livePieces[0] > -1 && livePieces[0] < 10) || !(livePieces[1] > -1 && livePieces[1] < 10)) {
			throw new Exception("Invalid range of livePieces");
		}

		// unplaced + live pieces are within range 3 - 9
		int p1 = unplacedPieces[0] + livePieces[0];
		int p2 = unplacedPieces[1] + livePieces[1];
		if (p1 < 3 || p1 > 9) {
			throw new Exception("Cannot have more than nine (9) pieces");
		}
		if (p2 < 3 || p2 > 9) {
			throw new Exception("Cannot have more than nine (9) pieces");
		}

		// update data
		this.boardLoc = grid;
		this.playerTurn = playerTurn;
		this.unplacedPieces = unplacedPieces;
		this.livePieces = livePieces;
	}
	
	
	// setters and getters for dealing with the board
	private void PlacePiece(Integer playerNum, Integer location) {
		// places a piece at a location and decrements unplaced count
		unplacedPieces[playerNum]--;
		boardLoc[location] = playerNum + 1;
	}
	
	
	private void MovePiece(Integer playerNum, Integer locationTo, Integer locationFrom) {
		// moves a pieces from one location to another
		boardLoc[locationTo] = playerNum + 1;
		boardLoc[locationFrom] = 0;
	}
	

	private void RemovePiece(Integer location) {
		// removes a piece at location
		Integer player = boardLoc[location] - 1;
		livePieces[player]--;
		boardLoc[location] = 0;		
	}

	public void takeInput(int location) {
		if(moveInProgress) {
			finishMovement(location);
			
		}else if(gameState==GameStates.remove) {
			processRemove(location);
		}else if(IsPlacementStage()) {
			processPlacement(location);
		}else {
			if(validFirstClick(location,playerTurn)) {
				prevClick=location;
				moveInProgress=true;
				
			}
			dispStatus="";
		}
			
	}
	private boolean validFirstClick(int loc, int player) {
		
		return IsPlayersPiece(player,loc);
		
	}

	private void processPlacement(int location) {
		Move currentMove=new Move(playerTurn,location);
		
		if(IsValidMovement(currentMove)) {
			TakeAction(currentMove);
		}else {
			dispStatus="";
		}
		
	}
	private void processRemove(int location) {
		
		if(IsValidRemoval(playerTurn,location)) {
			Move currentMove=new Move(playerTurn,location,true);
			TakeAction(currentMove);
			prevClick=0;
			moveInProgress=false;
		}else {
			dispStatus="";
		}
	}
	private void finishMovement(int location) {
		Move currentMove=new Move(playerTurn,location,prevClick);
		
		if(IsValidMovement(currentMove)) {
			
			TakeAction(currentMove);
			prevClick=0;
			moveInProgress=false;
			
		}else {
			dispStatus="Invalid Movement\n";
			prevClick=0;
			moveInProgress=false;
		}
		
		
		
	}
	public void TakeAction(Move currMove){

		

		if(currMove.isRemove()){
			try {
				RemoveMan(currMove);
			} catch (Exception e) {
				
				dispStatus="Invalid Removal";
			} 
			
		}else if(currMove.isPlacement()){

			try {
				MakeMove(currMove);
			} catch (Exception e) {
				
				dispStatus="Invalid Placement";
			} 

		}else{
			try {
				MakeMove(currMove);
			} catch (Exception e) {
				
				dispStatus="Invalid Movement";
			} 
		}
		
		
		dispStatus=currMove.toString(getPlayerName(currMove.getPlayerTurn()));
		
  }

	public boolean IsPlayersTurn(Integer playerNum) {
		// checks if it is a player's turn
		return (playerTurn == playerNum);
	}
	
	
	public boolean IsValidLoc(Integer location) {
		// checks if the location is valid for our data structure
		return !(location < 0 || location > 23);
	}
	
	
	public boolean IsEmpty(Integer location) {
		// checks if location is empty
		return (boardLoc[location] == 0);
	}
	
	
	public Integer GetPlayersTurn() {
		// gets players turn
		return playerTurn;
	}
	

	public String GetPlayersNameTurn() {
		// gets players turn
		switch (playerTurn) {
			case 0:
				return getPlayerOneName();
			case 1:
				return getPlayerTwoName();
		}
		return "NotFound";
	}
	
	
	public boolean IsPlayersPiece(Integer playerNum, Integer location) {
		// checks if the location's piece is the player's
		return (boardLoc[location] == (playerNum + 1));
	}
	
	
	public boolean HasUnplacedPieces(Integer playerNum) {
		// checks if a player has unplaced pieces
		return (unplacedPieces[playerNum] > 0);
	}
	
	
	public boolean AreAdjacent(Integer loc1, Integer loc2) {
		// checks if the location are adjacent
		return (Arrays.asList(adj.get(loc1)).contains(loc2));
	}
	
	
	public Integer NumUnplacedPieces(Integer playerNum) {
		// getter for number of unplaced pieces
		return unplacedPieces[playerNum];
	}


	public Integer NumLivePieces(Integer playerNum) {
		// getter for number of live pieces
		return livePieces[playerNum];
	}


	public boolean IsPlacementStage() {
		// getter for if it is the placement stage 
		// if not, it is the movement stage
		return (HasUnplacedPieces(0) || HasUnplacedPieces(1));
	}

	
	public boolean CanFly(Integer playerNum) {
		/* Checks if a player can fly, this means the player has 3 or less pieces */
		return livePieces[playerNum] <= 3;
	}
	
	
	public boolean IsMill(Integer location) {
		/* checks if the location is part of a mill
		 * A mill is formed if a player has three pieces in a straight line
		 * either vertical or horizontal */
		
		// get the value of the location
		Integer playerNum = boardLoc[location];
		// trivial case - is empty
		if (playerNum == 0) {
			return false;
		}
		Integer[] first, second;
		first = new Integer[3]; 
		second = new Integer[3];
		// search the array of possMills search for the two possiblities
		// of this lcoation
		boolean isFirst = true;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 3; j++) {
				if (possMills[i][j] == location) {
					// not this is just a reference
					if (isFirst) {
						first = possMills[i];
						isFirst = false;
					}
					else {
						second = possMills[i];
					}
				}
			}
		}
		if (boardLoc[first[0]] == playerNum && boardLoc[first[1]] == playerNum && boardLoc[first[2]] == playerNum) {
				return true;
		}
		if (boardLoc[second[0]] == playerNum && boardLoc[second[1]] == playerNum && boardLoc[second[2]] == playerNum) {
				return true;
		}
		return false;
	}
	
	
	public boolean HasLegalMoves(Integer playerNum) {
		// checks if a player has a possible move
		// if during placement
		if (HasUnplacedPieces(playerNum) || HasUnplacedPieces((playerNum+1)%2)) {
			return true;
		}
		// if looking for movement
		if (gameState == GameStates.move) {
			// if they can fly, there is a guaranteed move
			if (CanFly(playerNum)) {
				return true;
			}
			// otherwise start with location 0 
			// see if the player is in that position
			// and there are open adjacent spot
			int i = 0;
			while (i < 24) {
				if (boardLoc[i] == (playerNum + 1)) {
					Integer[] possibleAdj = adj.get(i);
					for (int j = 0; j < possibleAdj.length; j++) {
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
		if (gameState == GameStates.remove) {
			int i = 0;
			while (i < 24) {
				if (boardLoc[i] == ((playerNum + 1) % 2)) {
					if (!IsMill(i) || everyPieceAMill((playerNum))) {
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


	public boolean everyPieceAMill(int playerNum) {
		
		for (int x=0;x<24;x++) {
			if (IsPlayersPiece(playerNum,x) && !IsMill(x)) {
				return false;
			}
		}
		
		return true;
	}
	

	public GameStates GetGameState() {
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


	public ArrayList<Move> GetPossibleMoves() {
		//ToDo: find possible moves and return as list of move classes
		
		if (gameState==GameStates.remove) {
			return getPossibleRemovals();
		}
		
		if (IsPlacementStage()) {
			return getPossiblePlacements();
		} else {
			return getPossibleMovements();
		}
	
	}


	public ArrayList<Move> getPossibleRemovals(){ 
		/*Returns all possible moves for a removal*/
		ArrayList<Move> possibleRemovals=new ArrayList<Move>();		
		
		for (int x=0;x<boardLoc.length;x++) {
			if (IsValidRemoval(playerTurn,x)) {
				
				Move currMove=new Move(playerTurn,x,true); //if the location and turn is valid, create a new move
				possibleRemovals.add(currMove);
			}
		}
		
		return possibleRemovals;
		
	}


	public ArrayList<Move> getPossiblePlacements(){
		/*If we are in the placement stage, we simply create a new move based on a players piece and
		 * every empty place*/
		ArrayList<Move> possiblePlacements=new ArrayList<Move>();
		
		
		for(int x=0;x<boardLoc.length;x++) {
			if(IsValidMovement(playerTurn,x)) {
				Move currMove=new Move(playerTurn,x);
				possiblePlacements.add(currMove);
				
			}
		}
		
		return possiblePlacements;
	}


	public ArrayList<Move> getPossibleMovements(){
		ArrayList<Move> possibleMovements=new ArrayList<Move>();		
		ArrayList<Integer> playerLocs=getPlayerLocs(playerTurn);
		
		for(int locs:playerLocs) {
			if(CanFly(playerTurn)) {
				for(int x=0;x<24;x++) {
					if(IsValidMovement(playerTurn,x,locs)) {
						if(x!=locs) {
							Move currMove=new Move(playerTurn,x,locs);
							possibleMovements.add(currMove);
						}
					}
				}
			}
			else {	
				Integer[] adjLocs=adj.get(locs);
				for(int i=0;i<adjLocs.length;i++) {
					if(IsValidMovement(playerTurn,adjLocs[i],locs)) {
						Move currMove=new Move(playerTurn,adjLocs[i],locs);
						possibleMovements.add(currMove);
					}
				}
			}
		}		
		return possibleMovements;
	}


	public ArrayList<Integer> getPlayerLocs(int player){
		ArrayList<Integer> playerLocs=new ArrayList<Integer>();
		
		for(int x=0;x<boardLoc.length;x++) {
			if(boardLoc[x]==player+1) {
				playerLocs.add(x);
			}			
		}
		
		return playerLocs;
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
		if (!IsPlayersTurn(playerNum)) {
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
		if (GetGameState() != GameStates.move) {
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
		if (!(AreAdjacent(locationTo, locationFrom) || CanFly(playerNum))) {
			return false;
		}
		// check game state
		if (GetGameState() != GameStates.move) {
			return false;
		}
		return true;
	}
	public boolean IsValidMovement( Move currMove) {
		/* Method to check if a particular movement is valid 
		 * movement. Works with placement or movement */
		// check that both locations exist
						
		boolean placement=currMove.isPlacement();
		int playerNum=currMove.getPlayerTurn();
		int locationTo=currMove.getLocationTo();
		
		if (!IsValidLoc(locationTo)) {
			return false;
		}
		if (!IsPlayersTurn(playerNum)) {
			return false;
		}
		// check that the location to is empty
		if (!IsEmpty(locationTo)) {
			return false;
		}
		
		if(!placement) {
			
			int locationFrom=currMove.getLocationFrom();
			
			if (!IsValidLoc(locationFrom)) {
				return false;
			}
			
			// check that the player has no piece's left
			if (HasUnplacedPieces(playerNum)) {
				return false;
			}
			// check that the locationFrom is the correct player
			if (!IsPlayersPiece(playerNum, locationFrom)) {
				return false;
			}		
			// check if the two locations are adjacent or if flying is valid
			// !Adj && !CanFly <==> !(Adj || CanFly)
			if (!(AreAdjacent(locationTo, locationFrom) || CanFly(playerNum))) {
				return false;
			}
			
		}else {
			if (!HasUnplacedPieces(playerNum)) {
				return false;
			}
		}
		// check game state
		if (GetGameState() != GameStates.move) {
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
		if (!IsPlayersTurn(playerNum)) {
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
		if (GetGameState() != GameStates.remove) {
			return false;
		}
		// check if the location is a part of a mill
		if (IsMill(location)) {
			
			boolean allItemsInMill=true;
			
			for(int x=0;x<24;x++) {
				if(IsPlayersPiece(((playerNum + 1) % 2), x) && !IsMill(x)) {
					allItemsInMill=false;
				}
			}
			return allItemsInMill;
		}
		return true;	
	}
	
	
	
	// public functions for making moves
	public boolean MakeMove(Integer playerNum, Integer location) throws Exception {
		/* Make the move (with one location argument this will be placement) 
		 * and return if a mill has formed
		 * If the move is not valid, this will throw an error */
		if (!IsValidMovement(playerNum, location)) {
			throw new Exception("Invalid placement");
		}
		// otherwise is valid move
		PlacePiece(playerNum, location);
		Boolean formedMill = IsMill(location);
		if (formedMill) {
			gameState = GameStates.remove;
		}
		else {
			playerTurn = (playerTurn + 1) % 2;
		}
		return (formedMill);
	}
	
	
	
	public boolean MakeMove(Integer playerNum, Integer locationTo, Integer locationFrom) throws Exception{
		/* Make the move (with two location arguments this will be movement) 
		 * and return if a mill has formed
		 * If the move is not valid, this will throw an error */
		if (!IsValidMovement(playerNum, locationTo, locationFrom)) {
			throw new Exception("Invalid movement");
		}
		// otherwise is valid move
		MovePiece(playerNum, locationTo, locationFrom);
		Boolean formedMill = IsMill(locationTo);
		if (formedMill) {
			gameState = GameStates.remove;
		}
		else {
			playerTurn = (playerTurn + 1) % 2;
		}
		return (formedMill);
	}
	
	public boolean MakeMove(Move currMove) throws Exception {
		/* Make the move, check if it is placement or movement
		 * and return if a mill has formed
		 * If the move is not valid, this will throw an error */
		int locationTo= currMove.getLocationTo();
		int playerNum=currMove.getPlayerTurn();
		boolean placement=currMove.isPlacement();
		
		if(placement) { //placement
			if (!IsValidMovement(currMove)) {
				throw new Exception("Invalid placement");
			}
			// otherwise is valid move
			PlacePiece(playerNum, locationTo);
			Boolean formedMill = IsMill(locationTo);
			if (formedMill) {
				gameState = GameStates.remove;
			}
			else {
				playerTurn = (playerTurn + 1) % 2;
			}
			return (formedMill);
		}else { //Movement
			int locationFrom=currMove.getLocationFrom();
			
			if (!IsValidMovement(currMove)) {
				throw new Exception("Invalid movement");
			}
			// otherwise is valid move
			MovePiece(playerNum, locationTo, locationFrom);
			Boolean formedMill = IsMill(locationTo);
			if (formedMill) {
				gameState = GameStates.remove;
			}
			else {
				playerTurn = (playerTurn + 1) % 2;
			}
			return (formedMill);
			
		}
	}
	
	
	
	public void RemoveMan(Integer playerNum, Integer location) throws Exception {
		/* Remove a piece
		 * If the move is not valid, this will throw an error */
		if (!IsValidRemoval(playerNum, location)) {
			throw new Exception("Invalid movement");
		}
		// otherwise is valid move
		RemovePiece(location);
		gameState = GameStates.move;
		playerTurn = (playerTurn + 1) % 2;
	}
	public void RemoveMan(Move currMove) throws Exception {
		/* Remove a piece
		 * If the move is not valid, this will throw an error */
		
		int playerNum=currMove.getPlayerTurn();
		int location=currMove.getLocationTo();
		if (!IsValidRemoval(playerNum, location)) {
			throw new Exception("Invalid movement");
		}
		// otherwise is valid move
		RemovePiece(location);
		gameState = GameStates.move;
		playerTurn = (playerTurn + 1) % 2;
	}


	public String getPlayerOneName() {
		return playerOneName;
	}


	public void setPlayerOneName(String playerOneName) {
		this.playerOneName = playerOneName;
	}


	public String getPlayerTwoName() {
		return playerTwoName;
	}
	public String getPlayerName(int playerNum) {
		if(playerNum==0) {
			return getPlayerOneName();
		}
		if(playerNum==1) {
			return getPlayerTwoName();
		}
		
		else return "invalid player";
	}

	public void setPlayerTwoName(String playerTwoName) {
		this.playerTwoName = playerTwoName;
	}
	
	public String getDispStatus() {
		
		
		return dispStatus;
	}
	
	public int getScore() {
		
		int ownPieces=NumLivePieces(playerTurn);
		int otherPieces=NumLivePieces((playerTurn+1)%2);
		
		return ownPieces - otherPieces;
	}
	public boolean equals(Board otherBoard) {
		if(!(this.gameState==otherBoard.GetGameState())) {
			return false;
		}
		if(!(java.util.Arrays.deepEquals(this.boardLoc,otherBoard.boardLoc))) {
			return false;
		}
		if(!(this.playerTurn==otherBoard.playerTurn)) {
			return false;
		}
		if(!(java.util.Arrays.deepEquals(this.unplacedPieces,otherBoard.unplacedPieces))) {
			return false;
		}
		if(!(java.util.Arrays.deepEquals(this.livePieces,otherBoard.livePieces))) {
			return false;
		}
		
		return true;
		
	}
	
}