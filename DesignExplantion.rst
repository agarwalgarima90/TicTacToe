=====================
Design Specifications
=====================

Overall Class Structure
=======================

* com.mygames.App
* com.mygames.Colorify
* com.mygames.Game
* com.mygames.GameBoard
* com.mygames.InputParser
* com.mygames.Move
* com.mygames.Player
    * com.mygames.ComputerPlayer
    * com.mygames.HumanPlayer
    * com.mygames.HumanPlayerSimulation

App.java
--------

This is the Main Class of the Application. Its task is to initialize the Input
Parser class and run the Game.

Colorify.java
-------------

This is the class created to setup colors for print statements. Each method is
named specific to the color it provides. Whatever String is passed to the
function, it prints that in the psecified color.  The methods are kept as
static so that no instance is needed to call them.

Game.java
---------
This is the class which performs the operations to manage the Game. Its main
function is the runGame() which runs ina loop until someone wins or the game is
a draw.

GameBoard.java
--------------

This is the class which performs the operations of the GameBoard. It manages
the moves entered by the players and prints and updates the board accordingly.
It has a constructor which creates the board with the required size and also
initializes all the cells with a blank.

InputParser.java
----------------

This is the class which reads the input json file specified by the user. The
information is used to set up the Players accordingly. It also has a basic
level of error checking added. As of now only 3 Players (2 Humans and 1
Computer) is supported.

Move.java
---------

This class is used to manage the moves. It consists of three parameters, i.e.
row, column and the symbol.

Player.java
-----------

This is an abstract class to manage the operations of a Player. It holds an
abstract method as ``getNextMove`` which needs to be implemented by the child
classes to get the next move of the Player. The constructor initializes the
Player with the name, the symbol and the player type. This class is extended by
two child classes : ``ComputerPlayer`` and ``HumanPlayer``. This class is
written such as any type of Player can easily extend it. For example the
``HumanPlayerSimulation`` class extends it to add simulations to the game.

ComputerPlayer.java
-------------------

This is the class to manage the Player which has the type as Computer. It
implements the ``getNextMove`` function and generates the move(row, col) which
the computer player would like to make on the board.

HumanPlayer.java
----------------

This is the class to manage the Player which has type as Human. It implements
the ``getNextMove`` function where it takes input from user via console and
uses taht move to make an entry on the board with corresponding symbol.

HumanPlayerSimulation.java
--------------------------

This is the class which extends the ``Player`` class. The use of this class is
to simulate the moves of each player and play the game. It is highly useful for
testing the functionality of the Game. For running any new simulation the
developer just needs to create a new Json Input File and setup the moves and
run the game with the file.

Workflow
========

-   App class initializes the ``InputParser`` class.
-   The ``InputParser`` class parses the input and creates the players and initializes other important arguments.
-   The ``Game`` class is then initialized with the ``Players`` and board details from ``InputParser``.
-   The ``App`` class then calls the ``runGame()`` function of Game class.
-   ``runGame()`` function iterates over the Vector of players passed to it asks each player for the move.
-   The ``HumanPlayer`` class takes input from the user from the console, while ``HumanPlayerSimulation`` class takes the input from the file parsed by the ``InputParser``.
-   The ``ComputerPlayer`` class chooses the input after finding the most suitable move.
-   The game runs until someone has won or the game has drawn.


Testing
=======

Junit Testing framework has been used to the test the algorithm of getting the
computer moves. Json File Format mentioned in the README.rst file is used to
write a testcase. The ``isTest`` flag is set to true and the moves of the
``HumanPlayer`` and ``expectedMoves`` of the ``ComputerPlayer`` needs to be
specified for the test to run. 

For running the tests you can use the command ``mvn test``.

