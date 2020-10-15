package homework.hw1.task1.networkFileParser

import homework.hw1.task1.Computer
import homework.hw1.task1.OperatingSystem
import homework.hw1.task1.networkFileParser.exceptions.FileReadException
import homework.hw1.task1.networkFileParser.exceptions.NetworkMatrixException
import java.io.File
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.Scanner

class NetworkFileParser(file: File) {
    private val scanner = Scanner(file)
    lateinit var computersNetworkMatrix: List<List<Int>>
    lateinit var computersArray: List<Computer>

    init {
        readComputerNetworkFile()
    }

    private fun readComputerNetworkFile() {
        try {
            val computersNumber = getComputersNumber()
            computersNetworkMatrix = getComputersNetworkMatrix(computersNumber)
            computersArray = getComputersArray(computersNumber)
            if (!checkNetworkForCovering(computersNetworkMatrix, computersArray)) {
                throw NetworkMatrixException("Virus can't get to one of computer. Wrong matrix")
            }
        } catch (exception: NumberFormatException) {
            handleException(exception)
        } catch (exception: IllegalArgumentException) {
            handleException(exception)
        } catch (exception: NoSuchElementException) {
            handleException(exception)
        }
    }

    private fun getComputersNumber(): Int {
        val computersNumber = scanner.nextLine().toInt()
        require(computersNumber > 0) {
            "The number of computers should be positive"
        }
        return computersNumber
    }

    private fun getComputersNetworkMatrix(computersNumber: Int): List<List<Int>> {
        val computersNetworkMatrix = mutableListOf<List<Int>>()
        for (i in 0 until computersNumber) {
            val currentMatrixLine = scanner.nextLine()
                .split(" ")
                .map { value -> value.toInt() }
            for (j in currentMatrixLine.indices) {
                val currentValue = currentMatrixLine[i]
                require(currentValue == 1 || currentValue == 0) {
                    "Matrix value should be 1 or 0"
                }
                if (j == 1) {
                    require(currentValue != 1) {
                        "Matrix value at position (i, i) should be 0"
                    }
                }
            }
            computersNetworkMatrix.add(currentMatrixLine)
        }
        return computersNetworkMatrix.toList()
    }

    private fun getComputersArray(computersNumber: Int): List<Computer> {
        val computersArray = mutableListOf<Computer>()
        for (i in 0 until computersNumber) {
            val currentComputerDataString = scanner.nextLine()
            require(currentComputerDataString.matches("\\w+, ([01])\\.\\d+, (true|false)".toRegex())) {
                "Wrong current computer info $currentComputerDataString"
            }
            val currentComputerData = currentComputerDataString.split(", ")
            val operatingSystemName = currentComputerData[0]
            val computerIsInfected = currentComputerData[2]
            val operatingSystemInfectingChance = currentComputerData[1].toDouble()
            require(operatingSystemInfectingChance in 0.0..1.0) {
                "Wrong infecting chance argument: $operatingSystemInfectingChance"
            }
            require(computerIsInfected == "true" || computerIsInfected == "false") {
                "Wrong infection state argument: $computerIsInfected"
            }
            computersArray.add(
                Computer(
                    OperatingSystem(
                        operatingSystemName,
                        operatingSystemInfectingChance
                    ),
                    computerIsInfected.toBoolean()
                )
            )
        }
        return computersArray
    }

    private fun checkNetworkForCovering(
        computersNetworkMatrix: List<List<Int>>,
        fileComputersArray: List<Computer>
    ): Boolean {
        // first - neighborsList; second - isChecked
        val computersArray = Array(computersNetworkMatrix.size) {
            mutableListOf<Int>() to false }
        for (i in computersNetworkMatrix.indices) {
            val currentRow = computersNetworkMatrix[i]
            for (j in currentRow.indices) {
                if (currentRow[j] == 1) {
                    computersArray[i].first.add(j)
                }
            }
        }
        var previousCheckedState: List<Boolean>
        var currentCheckedState = computersArray.map { it.second }
        var currentImmutabilityState = false
        while (!currentImmutabilityState && !checkForTotalNetworkCover(computersArray)) {
            previousCheckedState = computersArray.map { it.second }
            for (i in computersArray.indices) {
                computersArray[i].first.forEach {
                    computersArray[it] = computersArray[it].first to true
                }
            }
            currentCheckedState = computersArray.map { it.second }
            currentImmutabilityState = checkForImmutability(previousCheckedState, currentCheckedState)
        }
        if (currentImmutabilityState && !uncoveredComputersAreInfected(currentCheckedState, fileComputersArray)) {
            return false
        }
        return true
    }

    private fun checkForImmutability(
        previousState: List<Boolean>,
        currentState: List<Boolean>
    ): Boolean {
        var checkResult = true
        if (previousState.size != currentState.size) {
            checkResult = false
        }
        for (i in previousState.indices) {
            if (previousState[i] != currentState[i]) {
                checkResult = false
            }
        }
        return checkResult
    }

    private fun uncoveredComputersAreInfected(
        currentState: List<Boolean>,
        fileComputersArray: List<Computer>
    ): Boolean {
        for (i in currentState.indices) {
            if (!currentState[i] && !fileComputersArray[i].isInfected) {
                return false
            }
        }
        return true
    }

    private fun checkForTotalNetworkCover(computersArray: Array<Pair<MutableList<Int>, Boolean>>): Boolean {
        computersArray.forEach {
            if (!it.second) {
                return false
            }
        }
        return true
    }

    private fun handleException(innerException: Exception) {
        throw(FileReadException(
            "Problem occurred while reading file",
            innerException
        ))
    }
}
