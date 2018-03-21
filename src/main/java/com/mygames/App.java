package com.mygames;

/**
 * This is the main driving class.
 * It's role is to parse the input and run the Game.
 *
 * @author Garima Agarwal
 */
public class App {
  public static void main(String[] args) {
    if (args.length != 1) {
      Colorify.PrintRedLn("Incorrect number of arguments, please enter the input file.");
      System.exit(1);
    }

    InputParser userInput = new InputParser(args[0]);
    userInput.parseInput();

    Game newGame =
        new Game(userInput.getBoardSize(), userInput.getAllPLayers(), userInput.getDescription());

    newGame.runGame();
  }
}
