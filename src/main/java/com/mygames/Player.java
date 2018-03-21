package com.mygames;
/**
 * Abstract class of Player which has an abstract method getNextMove.
 *
 * @author Garima Agarwal
 */
public abstract class Player {

  private char symbol;
  private String name;
  private String type;

  Player(String name, char symbol, String type) {
    this.name = name;
    this.symbol = symbol;
    this.type = type;
  }

  boolean isComputer() {
    if (this.type.equals("computer")) {
      return true;
    } else {
      return false;
    }
  }

  public char getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public void setSymbol(char symb) {
    this.symbol = symb;
  }

  /**
   * Abstract methid to get the next move from player which needs to be implemented in the child classes.
   *
   * @param  board Board Array
   * @return move  Move object
   */
  public abstract Move getNextMove(char[][] board);
}
