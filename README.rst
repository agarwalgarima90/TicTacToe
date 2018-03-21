###################
How to run the code
###################


.. contents:: 

==============
Pre-requisites
==============

The assignment is developed and tested on Ubuntu 16.04 Desktop LTS.

Here is the output of ``uname -a`` command and the ``/etc/lsb-release``

::


    $ uname -a
    Linux myHostName 4.4.0-21-generic #37-Ubuntu SMP Mon Apr 18 18:33:37 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux

::

    $ cat /etc/lsb-release
    DISTRIB_ID=Ubuntu
    DISTRIB_RELEASE=16.04
    DISTRIB_CODENAME=xenial
    DISTRIB_DESCRIPTION="Ubuntu 16.04 LTS"

* For running the code you will need the following

	* ``maven``
	* ``openjdk``

* For generating documentation you will need the following

	* ``graphviz``

* For details on how to install the required packages on Ubuntu 16.04 - read the document ``setupUbuntu.rst``.

* Script ``setupUbuntu.sh`` will help you in setting up the machine.



============================
How to run the code on Linux
============================

Run the file ``driver.sh`` with the appropriate option to get the desired result. The script is a wrapper around ``mvn`` command. The script supports three options ``-h``, ``-s``, ``-m``, ``-t``, ``-d``.


``-h`` option
=============

This is useful to get the information about all supported options for the script.

Example
-------

::

	$ ./driver.sh -h

	==============
	Printing  Help
	==============
	-m -f [FILENAME] For running a specific game. Also use it for running manual games or specific simulations.
	-s For running the simulations of the game. Uses the folder src/main/resources/ for files.
	-t For running the tests for the game. Uses the folder  for running the tests.
	-d Build the javadoc for the project.


``-m -f FILENAME`` option
=========================

This option is useful for running a game with a predefined set of inputs. For
more details about the json file read the sections ``Json File Format``.

Example
-------

::

        $ ./driver.sh -m -f src/main/resources/manual3x3game.json 
        [INFO] Scanning for projects...
        [INFO] 
        [INFO] ------------------------------------------------------------------------
        [INFO] Building TicTacToe 1.0-SNAPSHOT
        [INFO] ------------------------------------------------------------------------

        >>>>>>>>>> Snipped <<<<<<<<<<<<<

        gameDriver: INFO : Sucessfully built the code base
        gameDriver: INFO : Executable target/TicTacToe-1.0-SNAPSHOT-shaded.jar is present
        Running game with description:Manual Human moves for a 3X3 board
        Starting the Game. Initial board is empty 

            C1   C2   C3  
        R1 |   │   │  
        _______________
        R2 |   │   │  
        _______________
        R3 |   │   │  
        Garima's turn:
        Please enter row and column value from 1 to 3 in the format row,column

``-s`` option
=============

The repository has several simulation files. The simulation files has
predefined moves for the human players but not for the computer player. Running
``driver.sh -s`` will take all the simulation files from the repository
``src/main/resources/`` folder and run the game with them.

**These simulation files are very useful if you want to check the behaviour of
computer for a predefined set of moves.**

Example
-------

::

        $ ./driver.sh -s
        [INFO] Scanning for projects...
        [INFO] 
        [INFO] ------------------------------------------------------------------------
        [INFO] Building TicTacToe 1.0-SNAPSHOT
        [INFO] ------------------------------------------------------------------------
        [INFO] 
        [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ TicTacToe ---


        >>>>>>>>>> Snipped <<<<<<<<<<<<<


        R2 | C │ X │ C │ O
        ____________________
        R3 |   │   │ X │
        ____________________
        R4 |   │   │   │ X

        Garima has won the Game.
        gameDriver: Sucessfully ran the game file src/main/resources/simulation02.json

``-t`` option
==============

This option is useful to run the Junit Test Cases. The test case input files
are present in the folder ``src/test/resources/`` directory. **For adding more
test cases you just need to add more files to the mentioned directory.**

Example
-------

::
        
        $ ./driver.sh -t
        [INFO] Scanning for projects...
        [INFO]
        [INFO] ------------------------------------------------------------------------
        [INFO] Building TicTacToe 1.0-SNAPSHOT
        [INFO] ------------------------------------------------------------------------
        [INFO]
        [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ TicTacToe ---

        >>>>>>>>>> Snipped <<<<<<<<<<<<<

        ======================================
        Test ended for filename: src/test/resources/test01.json
        ======================================
        Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.314 sec

        Results :

        Tests run: 4, Failures: 0, Errors: 0, Skipped: 0

        [INFO] ------------------------------------------------------------------------
        [INFO] BUILD SUCCESS
        [INFO] ------------------------------------------------------------------------
        [INFO] Total time: 1.793 s
        [INFO] Finished at: 2018-03-20T19:07:50+05:30
        [INFO] Final Memory: 11M/303M
        [INFO] ------------------------------------------------------------------------
        gameDriver: Sucessfully ran the tests


``-d`` option
==============

This option is useful to generate the documentation.
The result HTML file ``index.html`` is present in ``target/site/apidocs`` folder.

Example
-------

::

	$ ./driver.sh -d
	[INFO] Scanning for projects...
	[INFO]
	[INFO] ------------------------------------------------------------------------
	[INFO] Building TicTacToe 1.0-SNAPSHOT
	[INFO] ------------------------------------------------------------------------

	>>>>>>>>>> Snipped <<<<<<<<<<<<<

	[INFO] ------------------------------------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO] ------------------------------------------------------------------------
	[INFO] Total time: 6.666 s
	[INFO] Finished at: 2018-03-21T13:26:20+05:30
	[INFO] Final Memory: 12M/31M
	[INFO] ------------------------------------------------------------------------
	gameDriver: INFO : Sucessfully built the java doc.
	gameDriver: Open the file from a browser : target/site/apidocs/index.html

Json File Format
================

The inputs must be mentioned in a json file with the following format.

::

        {
            "fileDescription": "Manual Human moves for a 3X3 board",
            "isSimulation": false,
            "isTest": false,
            "boardSize": 3,
            "numPlayers": 3,
            "playerInfo": [{
                    "name": "Garima",
                    "type": "human",
                    "symbol": "X",
                    "moves": null
                },
                {
                    "name": "Rishi",
                    "type": "human",
                    "symbol": "O",
                    "moves": null
                },
                {
                    "name": "Computer",
                    "type": "computer",
                    "symbol": "C",
                    "expectedMoves": null
                }
            ]
        }


Explanation of the fields
-------------------------

#.  **fileDescription**: A comment describing the purpose of the file. 
#.  **isSimulation**: Field for running a simulation. Possible values are ``true/false``. You need to fill the moves of the Human Player.
#.  **isTest**: Field for writing a test. You need to fill the moves of Humans as well as the expected moves of the computer player.
#.  **boardSize**: The number of rows the board will have.
#.  **numPlayers**: The number of players for the game. This is not used as of now. This gives the freedom to expand the game to more players.
#.  **playerInfo**: The details of the participating players.
    
    *   **name**: Name of the player.
    *   **type**: Type of the player. Possible values are ``human/computer``.
    *   **symbol**: The symbol which will be used by the player for marking on the board.
    *   **moves**: If it is a simulation or a test we need to mention the moves. If it is a simulation, moves in computer player are ignored, whereas in case of a test, the ``expectedMoves`` of a computer must be filled.


===================================
Building the code without driver.sh
===================================

Build without running tests
============================

``mvn clean install -Dmaven.test.skip=true``

Build with running tests
========================

``mvn clean install``

==================================
Running the code without driver.sh
==================================

Running any game
=================


``java -jar target/TicTacToe-1.0-SNAPSHOT-shaded.jar FILENAME``

**for example**

``java -jar target/TicTacToe-1.0-SNAPSHOT-shaded.jar src/main/resources/manual3x3game.json``

Running Test
============

``mvn test``


Generating JavaDocs
====================

``mvn javadoc:javadoc`` 
