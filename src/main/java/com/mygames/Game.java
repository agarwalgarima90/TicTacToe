package com.mygames;

import java.util.Iterator;
import java.util.Vector;

/**
 * Provides the Game class which is the main class of the application reponsible for initializing everything required for the game like Players and Board.
 * In main function it has the loop which will ask every
 * user to make their move and after every move the board will be checked for
 * winning player.
 * 
 * @author Garima Agarwal
 */
public class Game {

  private int boardSize;
  private Vector players;
  private String gameDescription;

  Game(int boardSize, Vector playersVector, String gameDescription) {
    this.boardSize = boardSize;
    players = playersVector;
    this.gameDescription = gameDescription;
  }

  /**
   * Method to check if board size is valid or not Minimum : 3 Maximum : 10
   *
   * @param size                size of the board
   * @return <code>true</code>  If board size is within desired limit
   *         <code>false</code> Otherwise
   */
  private boolean isValidBoardSize(int size) {
    if (size <= 10 && size >= 3) {
      return true;
    } else {
      return false;
    }
  }

  public void runGame() {

    Colorify.PrintPurpleLn("====================================================================");
    Colorify.PrintPurpleLn("Running game with description:" + gameDescription);
    Colorify.PrintPurpleLn("====================================================================");
    GameBoard board = null;

    boolean isValidSize = isValidBoardSize(boardSize);
    if (isValidSize) {
      board = new GameBoard(boardSize);
    } else {
      Colorify.PrintRedLn("Invalid size entered for board. Defaulting it to 3. Happy Playing..!!");
      System.exit(-1);
    }

    Colorify.PrintDefault("Starting the Game. Initial board is empty ");
    boolean won = false;
    boolean isNextMovePossible = false;
    boolean endGame = false;

    while (won == false) {
      Iterator it = players.iterator();
      while (it.hasNext()) {
        Player temp = (Player) it.next();
        boolean isValidMove = false;

        while (isValidMove == false) {
          board.printBoardForHumans();
          Move nextMove = temp.getNextMove(board.getBoard());
          isValidMove = board.evaluateMove(nextMove);
          if (isValidMove) {
            board.doNextMove(nextMove);
          } else {
            Colorify.PrintRedLn("Invalid Move. The place is already filled.");
          }
        }

        won = board.checkIfWon();
        if (won == true) {
          board.printBoardForHumans();
          Colorify.PrintDefault("");
          Colorify.PrintPurpleLn(temp.getName() + " has won the Game.");
          break;
        }

        isNextMovePossible = board.isNextMovePossible();
        if (isNextMovePossible) {
          continue;
        } else {
          endGame = true;
        }
      }
      if (endGame == true) {
        break;
      }
    }

    if (won == false) {
      board.printBoardForHumans();
      Colorify.PrintGreenLn("The match is a draw");
    }
  }
}
