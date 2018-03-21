package com.mygames;

import java.util.Scanner;

/**
 * HumanPlayer Class which is inherited from Player Base class and implements
 * the abstract functions in addition to its own functionality.
 *
 * @author Garima Agarwal 
 */
class HumanPlayer extends Player {

  HumanPlayer(String name, char symbol, String type) {
    super(name, symbol, type);
  }

  /**
   * Function to get the next move from the user via console.
   *
   * @param  board    Board Array
   * @return nextMove Next Move Object
   */
  public Move getNextMove(char[][] board) {
    int row = -1;
    int col = -1;

    // Take the move from the user.
    Scanner sc = new Scanner(System.in);

    Colorify.PrintGreenLn(this.getName() + "'s turn:");

    Colorify.PrintGreenLn(
        String.format(
            "Please enter row and column value from %d to %d in the format row,column.",
            1, board.length));

    boolean isValidInput = false;

    while (isValidInput == false) {
      String cellInput = sc.next();
      String[] tempSplit = cellInput.split(",");

      while (tempSplit.length != 2) {
        Colorify.PrintRedLn(
            String.format(
                "Incorrect format of input received. Please enter again in the format of 'row,column'."));
        cellInput = sc.next();
        tempSplit = cellInput.split(",");
      }

      try {
        row = Integer.parseInt(tempSplit[0]);
        col = Integer.parseInt(tempSplit[1]);
      } catch (NumberFormatException e) {
        Colorify.PrintRedLn(
            String.format(
                "Incorrect format of input received. Please enter again in the format of 'row,column'."));
        continue;
      }

      if (isValid(row, col, board) == false) {
        Colorify.PrintRedLn(
            String.format(
                "Out of board range input received !! Please enter valid row,col where you want to mark."));
        continue;
      }
      isValidInput = true;
    }

    Move nextMove = new Move(row - 1, col - 1, this.getSymbol());

    return nextMove;
  }

  /**
   * Check if the entered move is a valid based on the size of the board.
   *
   * @param  rowEntry           row entry made by user
   * @param  colEntry           col entry made by user
   * @param  board              instance of board
   * @return <code>true</code>  If valid move
   *         <code>false</code> Otherwise
   */
  private boolean isValid(int rowEntry, int colEntry, char[][] board) {
    if ((rowEntry > board.length)
        || (rowEntry < 1)
        || (colEntry > board.length)
        || (colEntry < 1)) {
      return false;
    } else {
      return true;
    }
  }
}
