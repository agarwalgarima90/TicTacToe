package com.mygames;
/**
 * This is the class to set up colors for different print statements.
 *
 * @author Garima Agarwal
 */
class Colorify {
  public static void PrintRedLn(String s) {
    System.out.println("\033[31m" + s + "\033[0m");
  }

  public static void PrintGreenLn(String s) {
    System.out.println("\033[32m" + s + "\033[0m");
  }

  public static void PrintBlue(String s) {
    System.out.print("\033[34m" + s + "\033[0m");
  }

  public static void PrintBlueLn(String s) {
    System.out.println("\033[34m" + s + "\033[0m");
  }

  public static void PrintPurpleLn(String s) {
    System.out.println("\033[35m" + s + "\033[0m");
  }

  public static void PrintBlankLine() {
    System.out.println();
  }

  public static void PrintDefault(String s) {
    System.out.println(s);
  }
}
