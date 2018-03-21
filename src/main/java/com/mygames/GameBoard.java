package com.mygames;
/**
 * This is the class which initializes the Game Board with specified size and creates an empty board to play.
 * Also with every move it does validations and update the board with the symbol accordingly.
 * 
 * @author Garima Agarwal 
 * 
 */
public class GameBoard {
  private char[][] board;

  /**
   * Parameterized Constructor to set the size of the board
   *
   * @param size size of the board
   */
  GameBoard(int size) {
    this.board = new char[size][size];
    initialize();
  }

  /** Method to initialize the board with all spaces. */
  private void initialize() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        board[i][j] = ' ';
      }
    }
  }
  /**
   * Method to fetch the size of the board
   *
   * @return length Board Size
   */
  public int getSize() {
    return board.length;
  }

  /**
   * Returns a 2D array representation which will be given to the players
   * So that they can decide their moves.
   *
   * @return board Array of Board
   */
  public char[][] getBoard() {
    return board;
  }

  /**
   * Method to do the actual move on the board and make the entry with
   * corresponding symbol based on the move passed.
   *
   * @param move The Move object
   */
  void doNextMove(Move move) {
    int row = move.getRow();
    int col = move.getCol();
    char symbol = move.getSymbol();
    board[row][col] = symbol;
  }

  /**
   * Method to print the current status of the board. 
   * This is required for the human player so that they can choose their moves.
   */
  public void printBoardForHumans() {
    int i;

    System.out.println();
    System.out.print("   ");
    for (i = 0; i < board.length; i++) {
      Colorify.PrintBlue(" C" + (i + 1) + " ");
    }
    System.out.println();

    for (i = 0; i < board.length; i++) {
      Colorify.PrintBlue("R" + (i + 1) + " | ");
      for (int j = 0; j < board.length; j++) {
        Colorify.PrintBlue("" + board[i][j]);
        if (j != board.length - 1) {
          Colorify.PrintBlue(" " + "|" + " ");
        }
      }

      if (i != board.length - 1) {
        Colorify.PrintBlankLine();
        for (int m = 0; m < ((board.length * 5)); m++) {
          Colorify.PrintBlue("_");
        }
        Colorify.PrintBlankLine();
      }
    }
    Colorify.PrintBlankLine();
  }

  /**
   * Method to evaluate the passed move if it can be entered on the board. 
   * It checks if the cell entered is available for entry or is already filled
   * and return true or false accordingly.
   *
   * @param move                The Move object
   * @return <code>true</code>  If the move can be entered
   *         <code>false</code> Otherwise
   */
  public boolean evaluateMove(Move move) {
    int row = move.getRow();
    int col = move.getCol();
    if (board[row][col] == ' ') {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method to check if all cells are filled or is there still a possibility of a move.
   *
   * @return <code>true</code>  If Board is still Empty for next move
   *         <code>false</code> Otherwise
   */
  public boolean isNextMovePossible() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == ' ') {
          return true;
        }
      }
    }
    return false;
  }

  private boolean checkRowsForWin() {
    boolean won = false;
    for (int i = 0; i <= board.length - 1; i++) {
      for (int j = 0; j < board.length - 1; j++) {
        if ((board[i][j] == board[i][j + 1]) && board[i][j] != ' ') {
          won = true;
        } else {
          won = false;
          break;
        }
      }
      if (won) {
        return won;
      }
    }
    if (won) {
      return won;
    }
    return false;
  }

  private boolean checkColumnsForWin() {
    boolean won = false;
    for (int m = 0; m <= board.length - 1; m++) { // col
      for (int n = 0; n < board.length - 1; n++) { // row
        if ((board[n][m] == board[n + 1][m]) && board[n][m] != ' ') {
          won = true;
        } else {
          won = false;
          break;
        }
      }
      if (won) {
        return won;
      }
    }
    if (won) {
      return won;
    }
    return false;
  }

  private boolean checkLeftDiagonalForWin() {
    boolean won = false;
    int i, j;
    for (i = 0, j = board.length - 1; i < board.length - 1 && j > 0; i++, j--) {
      if ((board[i][j] == board[i + 1][j - 1]) && board[i][j] != ' ') {
        won = true;
      } else {
        won = false;
        break;
      }
    }
    if (won) {
      return won;
    }
    return false;
  }

  private boolean checkRightDiagonalForWin() {
    boolean won = false;
    int m, n;
    for (m = 0, n = 0; m < board.length - 1 && n < board.length - 1; m++, n++) {
      if ((board[m][n] == board[m + 1][n + 1]) && board[m][n] != ' ') {
        won = true;
      } else {
        won = false;
        break;
      }
    }
    if (won) {
      return won;
    }
    return false;
  }

  /**
   * Method to check if after the last entry on the board, any player has won the game or not.
   *  It checks all rows, columns and two diagonals for it.
   *
   * @return <code>true</code>  If the Game is Won
   *         <code>false</code> Otherwise
   */
  public boolean checkIfWon() {
    boolean won = false;

    won = checkRowsForWin();
    if (won == true) {
      return won;
    }

    won = checkColumnsForWin();
    if (won == true) {
      return won;
    }

    won = checkRightDiagonalForWin();

    if (won == true) {
      return won;
    }

    won = checkLeftDiagonalForWin();

    if (won == true) {
      return won;
    }
    return false;
  }
}
