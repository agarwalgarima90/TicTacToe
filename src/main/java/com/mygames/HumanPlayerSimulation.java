package com.mygames;

/**
 * Class to return a predefined set of moves to the game. This helps in running
 * the simulation of the game.
 *
 * @author Garima Agarwal
 */
class HumanPlayerSimulation extends Player {

  private int[][] moves;
  private int currentMove;

  HumanPlayerSimulation(String name, char symbol, String type, int[][] movesList) {
    super(name, symbol, type);

    this.moves = new int[movesList.length][movesList[0].length];
    currentMove = 0;

    for (int i = 0; i < movesList.length; i++) {
      for (int j = 0; j < movesList[i].length; j++) {
        moves[i][j] = movesList[i][j];
      }
    }
  }

  /**
   * Function to capture the next move from the user via console
   *
   * @param  board    Board Array
   * @return nextMove Netx move object
   */
  public Move getNextMove(char[][] board) {

    int chosenRow = moves[currentMove][0] + 1;
    int chosenColumn = moves[currentMove][1] + 1;
    Colorify.PrintBlueLn(
        String.format(
            "%-15s has chosen to move row: %2d column: %2d.",
            this.getName(), chosenRow, chosenColumn));
    Move nextMove = new Move(moves[currentMove][0], moves[currentMove][1], this.getSymbol());
    currentMove++;

    return nextMove;
  }
}
