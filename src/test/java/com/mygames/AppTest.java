package com.mygames;


import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/** Unit test for simple App. */
@RunWith(value = Parameterized.class)
public class AppTest {
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  private String testFileName;

  public AppTest(String testFileName) {
    this.testFileName = testFileName;
  }

  @Parameters
  public static Collection data() {

    Vector fileNames = new Vector(10, 5);
    // Object[][] data = new Object[][];
    /*{ {"src/test/resources/test01.json"},
    	{"src/test/resources/test02.json"},
    	{"src/test/resources/test03.json"},
    	{"src/test/resources/test04.json"}
    }; */
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    String testFilesDirectory = "src/test/resources/";
    File folder = new File(testFilesDirectory);
    File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        String fileName = listOfFiles[i].getName();
        if (fileName.endsWith(".json")) {
          String fileRelativePath = testFilesDirectory + fileName;
          fileNames.addElement(fileRelativePath);
        } else {
          System.err.println("Skipping filename " + fileName + " as its not a json file");
        }
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println(
            "Not getting into directory in search of test files: " + listOfFiles[i].getName());
      }
    }

    Object[][] data = new Object[fileNames.size()][1];

    for (int i = 0; i < fileNames.size(); i++) {
      data[i][0] = fileNames.elementAt(i);
    }
    return Arrays.asList(data);
  }

  @Test
  public void checkComputerMoves() {
    System.out.println("======================================");
    System.out.println("Testing for file " + testFileName);
    System.out.println("======================================");

    InputParser userInput = new InputParser(testFileName);
    userInput.parseInput();
    int[][] computerExpectedMoves = userInput.getExpectedMovesOfComputer();
    int computerCurrentMove = 0;
    int size = userInput.getBoardSize();
    Vector playersVector = userInput.getAllPLayers();

    char[][] board = new char[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        board[i][j] = ' ';
      }
    }

    for (int i = 0; i < size; i++) {
      Iterator it = playersVector.iterator();

      while (it.hasNext()) {
        Player temp = (Player) it.next();

        Move nextMove = temp.getNextMove(board);
        board[nextMove.getRow()][nextMove.getCol()] = temp.getSymbol();

        if (temp.isComputer() == true) {

          try {
            int computerExpectedMoveRow = computerExpectedMoves[computerCurrentMove][0];
            int computerExpectedMoveCol = computerExpectedMoves[computerCurrentMove][1];
            assertEquals(nextMove.getRow(), computerExpectedMoveRow);
            assertEquals(nextMove.getCol(), computerExpectedMoveCol);
            computerCurrentMove++;
          } catch (AssertionError e) {
            throw e;
          }
        }
      }
    }

    System.out.println("======================================");
    System.out.println("Test ended for filename: " + testFileName);
    System.out.println("======================================");
  }
}
