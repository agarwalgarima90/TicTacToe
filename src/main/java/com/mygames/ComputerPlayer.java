package com.mygames;


/**
 * Computer Player class that is inherited from Player Base class.
 *
 * @author Garima Agarwal
 */
class ComputerPlayer extends Player {

  ComputerPlayer(String name, char symbol, String type) {
    super(name, symbol, type);
  }

  /**
   * Method to capture the next move from Computer.
   *
   * @param board     Board Array
   * @return nextMove The next move object
   */
  public Move getNextMove(char[][] board) {
    // Take the move from the user.
    int[] moves = new int[2];
    computeMove(board, moves);
    int chosenRow = moves[0] + 1;
    int chosenColumn = moves[1] + 1;
    Colorify.PrintBlueLn(
        String.format(
            "%-15s has chosen to move row: %2d column: %2d.",
            this.getName(), chosenRow, chosenColumn));
    Move nextMove = new Move(moves[0], moves[1], this.getSymbol());
    return nextMove;
  }

  /**
   * Method to compute the possible move of Computer based on the current arrangement of the board.
   *
   * @param board Board Array
   * @param moves Moves Array
   */
  private void computeMove(char[][] board, int[] moves) {
    outerloop:
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == ' ') {
          moves[0] = i;
          moves[1] = j;
          break outerloop;
        }
      }
    }
  }
}
