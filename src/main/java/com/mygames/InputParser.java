package com.mygames;
/** 
 * @author Garima Agarwal
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

/** InputParser class to read the contents of json file, and provide methods to initialize the player and get information from json file.
 */
class InputParser {

  private boolean isSimulation = false;
  private boolean isTest = false;
  private int boardSize = 0;
  private int numOfPlayers = 0;
  private String fileName = null;
  private Vector playersVector = null;
  private JsonNode playerInfo = null;
  private String fileDescription = null;

  InputParser(String fileName) {
    this.fileName = fileName;
  }

  public String getDescription() {
    return fileDescription;
  }

  public int getBoardSize() {
    return boardSize;
  }

  public Vector getAllPLayers() {
    return playersVector;
  }

  /**
   * Method to read the file to get information.
   * 1. size of the board 
   * 2. symbol for Player 1 3. symbol for Player
   * 
   * @param rootNode The JsonNode object
   */
  private void parseNameValues(JsonNode rootNode) {
    isSimulation = (rootNode.path("isSimulation")).asBoolean();
    isTest = (rootNode.path("isTest")).asBoolean();
    boardSize = (rootNode.path("boardSize")).asInt();
    numOfPlayers = (rootNode.path("numPlayers").asInt());
    playerInfo = rootNode.path("playerInfo");
    fileDescription = rootNode.path("fileDescription").asText();
  }

  private String getNameFromObj(JsonNode obj) {
    return (obj.path("name")).asText();
  }

  private char getSymbolFromObj(JsonNode obj) {
    return ((obj.path("symbol")).asText()).charAt(0);
  }

  Player getComputerPlayer(JsonNode obj, String playerType) {
    String name = getNameFromObj(obj);
    char symbol = getSymbolFromObj(obj);
    return new ComputerPlayer(name, symbol, playerType);
  }

  private void checkMovesForSimulation(JsonNode obj, int boardSize) {
    if ((obj.path("moves") == null)) {
      throw new RuntimeException("No moves provided for simulation");
    } else if ((obj.path("moves")).size() != boardSize) {
      throw new RuntimeException(
          "Number of moves present in simulation is not according to the board size.");
    }
  }

  private void isUniqueSymbols(Vector <Player> players) {

	  char symbol1 = (players.elementAt(0)).getSymbol();
	  char symbol2 = (players.elementAt(1)).getSymbol();
	  char symbol3 = (players.elementAt(2)).getSymbol();

	  if (symbol1 == symbol2 || symbol1 == symbol3 || symbol2 == symbol3) {
		  throw new RuntimeException("Two players should not have same symbols. Please correct the input file."); 
	  }
  }
  
  /**
   * Method to parse the input JSON file and validate.
   */
  public void parseInput() {

    int exitErrorCode = -1;
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode rootNode = mapper.readTree(new File(fileName));
      int humanPlayerCount = 0;
      int computerPlayerCount = 0;
      playersVector = new Vector();

      parseNameValues(rootNode);

      Iterator<JsonNode> players = playerInfo.elements();

      while (players.hasNext()) {
        JsonNode obj = players.next();
        String playerType = ((obj.path("type")).asText());
        String name = getNameFromObj(obj);
		Player tempPlayer = null;
        char symbol = getSymbolFromObj(obj);

        if (playerType.equals("human")) {
          humanPlayerCount++;
			if (humanPlayerCount >=3) {
			  throw new RuntimeException("Only two human players are allowed. Kindly correct the input file.");
			}
          if (this.isSimulation == true) {
            checkMovesForSimulation(obj, this.boardSize);

            int[][] movesList = readMovesIntoArray(obj.path("moves"), boardSize);
            tempPlayer = new HumanPlayerSimulation(name, symbol, playerType, movesList);
          } else if (this.isSimulation == false) {
            tempPlayer = new HumanPlayer(name, symbol, playerType);
          }
        } else if (playerType.equals("computer")) {
          computerPlayerCount++;
          if (computerPlayerCount >= 2) {
			  throw new RuntimeException("Only one computer player is allowed.");
          }
          tempPlayer = new ComputerPlayer (name, symbol, playerType);

        } else {
          throw new RuntimeException("Unknown playertype in json file: " + playerType);
        }

        playersVector.add(tempPlayer);
		
		if (computerPlayerCount >= 2) {
			throw new RuntimeException("Only one computer player is allowed. Kindly correct the input file.");
		}
		
      }

		isUniqueSymbols(playersVector);
      /* Incase the game is not a simulation then shuffle the players. */
      if (isSimulation == false) {
        Collections.shuffle(playersVector);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(exitErrorCode);
    }
  }

  private int[][] readMovesIntoArray(JsonNode movesNode, int boardSize) {
    int[][] movesArray = new int[boardSize][2];

    Iterator<JsonNode> moves = movesNode.elements();
    int i = 0;
    while (moves.hasNext()) {
      JsonNode temp = moves.next();
      for (int j = 0; j < temp.size(); j++) {
        movesArray[i][j] = (temp.get(j)).asInt();
      }
      i++;
    }
    return movesArray;
  }

  public int[][] getExpectedMovesOfComputer() {
    try {
      Iterator<JsonNode> players = playerInfo.elements();
      while (players.hasNext()) {
        JsonNode obj = players.next();
        String playerType = ((obj.path("type")).asText());
        if (isTest == true && playerType.equals("computer")) {
          if ((obj.path("expectedMoves") == null)) {
            System.out.println("No moves provided for testing");
            System.exit(1);
          } else {
            // Initialize Simulated Players
            int[][] movesList = readMovesIntoArray(obj.path("expectedMoves"), boardSize);
            return movesList;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
    return null;
  }
}
