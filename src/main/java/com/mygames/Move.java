package com.mygames;
/**
 * Class Move to operate on the moves entered.
 *
 * @author Garima Agarwal 
 */
class Move {
  private int row, col;
  private char symbol;

  Move(int r, int c, char symbol) {
    this.row = r;
    this.col = c;
    this.symbol = symbol;
  }

  int getRow() {
    return row;
  }

  int getCol() {
    return col;
  }

  char getSymbol() {
    return symbol;
  }
}
