#! /bin/bash

#
# Wrapper script over mvn commands to enables users run simulations,
# tests, and other games. This script also generates the javadoc.
#

DEBUG=0

# Macros
FAILURE=-1
SUCCESS=0
MSGTAG="gameDriver"

# Simulation Resources Directory
SIMULATION_FILES_DIRECTORY="src/main/resources/"

# Test Resources Directory
TEST_FILES_DIRECTORY=""

# Manual Games Directory
MANUAL_GAMES_DIRECTORY="src/main/resources/"

# Logging functions.

TARGET_DIR="target"
EXECUTABLE_JAR="TicTacToe-1.0-SNAPSHOT-shaded.jar"
EXECUTABLE_PATH="${TARGET_DIR}""/""${EXECUTABLE_JAR}"
CMDLINE="java -jar ${EXECUTABLE_PATH}"

# Maven commands
MAVEN_BUILD_COMMAND="mvn package -Dmaven.test.skip=true"
MAVEN_TEST_COMMAND="mvn test"
MAVEN_JAVADOC_COMMAND="mvn javadoc:javadoc"

logInfo()
{
	msg=${1}
	test ${DEBUG} != 0 && echo "${MSGTAG}: ${FUNCNAME[1]}:${BASH_LINENO[0]}: INFO: $msg"
	test ${DEBUG} == 0 && echo "${MSGTAG}: INFO : $msg"
}

logError()
{
	msg=${1}
	test ${DEBUG} != 0 && echo "${MSGTAG}: ${FUNCNAME[1]}:${BASH_LINENO[0]}: ERROR: $msg"
	test ${DEBUG} == 0 && echo "${MSGTAG}: ERROR: $msg"
}

buildJavadocs() {
	${MAVEN_JAVADOC_COMMAND}

	if [ "$?" == "0" ]; then
		logInfo "Sucessfully built the java doc."
		myEcho "Open the file from a browser : target/site/apidocs/index.html"
	else
		logError "Failed to build the java doc."
		exit ${FAILURE}
	fi
}

buildCodebase() {

	${MAVEN_BUILD_COMMAND}
	if [ "$?" == "0" ]; then
		logInfo "Sucessfully built the code base"
	else
		logError "Failed to build the code base"
		exit ${FAILURE}
	fi
}

help () {
	echo ""
	echo "=============="
	echo "Printing  Help"
	echo "=============="
	echo "-m -f [FILENAME] For running a specific game. Also use it for running manual games or specific simulations. Some manual file are kept in src/main/resources/ directory."
	echo "-s For running the simulations of the game. Uses the folder ${SIMULATION_FILES_DIRECTORY} for files."
	echo "-t For running the tests for the game. Uses the folder ${TEST_FILES_DIRECTORY} for running the tests."
	echo "-d Build the javadoc for the project. Open the file target/site/apidocs/index.html in any browser."
}


myEcho()
{
	msg=${1}
	echo "${MSGTAG}: ${msg}"
}

runGame() {
	FILENAME=${1}

	if [ "${FILENAME}" == "" ]; then 
		logError "No filename passed to the runGame function"
		return ${FAILURE}
	else
		if [ -f ${FILENAME} ]; then 
			${CMDLINE} ${FILENAME}

			# We are not bothered if the command failed. If we successfully ran
			# the command our job is done.
			return ${SUCCESS}
		else
			logError "No file ${file}. Returning."
			return ${FAILURE}
		fi
	fi
}

runSimulations() {
	
	simulationFiles=`find ${SIMULATION_FILES_DIRECTORY} | grep "simulation.*.json$"`

	for simulationFile in ${simulationFiles}; do 
		runGame ${simulationFile}
		if [ "$?" == 0 ]; then
			myEcho "Sucessfully ran the game file ${simulationFile}"
		else
			myEcho "Failed to run the game file ${simulationFile}"
		fi
	done 
}


checkConfiguration() {
	
	if [ -f "${EXECUTABLE_PATH}" ]; then
		logInfo "Executable ${EXECUTABLE_PATH} is present"
	else
		logError "Executable ${EXECUTABLE_PATH} is not present"
		exit ${FAILURE}
	fi
}

runTests() {
	${MAVEN_TEST_COMMAND}

	if [ "${?}" == "0" ]; then
		myEcho "Sucessfully ran the tests"
	else
		myEcho "Failed to run the test"
	fi
}

while getopts "dhsmtf:" arg; do
	case $arg in

		d)	
			OP="DOCS"
			;;
		s)
			OP="SIMULATION"
			;; 
		h)
			help
			exit 0
			;;
		m)
			OP="MANUAL"
			;;
		t)
			OP="TEST"
			;;
		f)	
			FILE=${OPTARG}
			;;

		*)
			help
			exit 1
			;;

	esac
done

if [ "${OP}" == "SIMULATION" ]; then 
	if [ $# -gt 1 ]; then
		myEcho "Extra arguments in the command line, exiting."
		help
		exit ${FAILURE}
	else
		buildCodebase
		checkConfiguration
		runSimulations
	fi
elif [ "${OP}" == "MANUAL" ]; then
	if [ "${FILE}" == "" ]; then
		myEcho "Must specify the filename using -f for manual game"
		myEcho "Exiting"
	elif [ $# -ne  3 ]; then
		myEcho "incorrect number of arguments in the command line, exiting."
		help
		exit ${FAILURE}
	else
		buildCodebase
		checkConfiguration
		runGame ${FILE}
	fi

elif [ "${OP}" == "TEST" ]; then
	if [ $# -gt 1 ]; then
		myEcho "extra arguments in the command line, exiting."
		help
		exit ${FAILURE}
	else
		buildCodebase
		checkConfiguration
		runTests
	fi

elif [ "${OP}" == "DOCS" ]; then
	if [ $# -gt 1 ]; then
		myEcho "Extra arguments in the command line, exiting."
		help
		exit ${FAILURE}
	else
		buildJavadocs
	fi
else
	logError "Error in command line. Exiting"
	help
	exit ${FAILURE}
fi
