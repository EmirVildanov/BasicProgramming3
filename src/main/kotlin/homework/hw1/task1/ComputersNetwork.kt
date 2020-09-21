package homework.hw1.task1

import homework.hw1.task1.probabilityGenerator.ProbabilityGenerator

class ComputersNetwork(
    private val computersNetworkMatrix: List<List<Int>>,
    private val computersArray: List<Computer>,
    private val probabilityGenerator: ProbabilityGenerator
) {

    private var infectionTurn = 0

    private fun checkForTotalInfection(computersArray: List<Computer>): Boolean {
        computersArray.forEach {
            if (!it.isInfected) {
                return false
            }
        }
        return true
    }

    private fun printStatistics(computersArray: List<Computer>) {
        println("Infection turn $infectionTurn. Current network state:")
        for (i in computersArray.indices) {
            println("$i ${computersArray[i]}")
        }
        println("")
    }

    private fun infectNeighbors(currentComputerIndex: Int) {
        val neighbors = computersNetworkMatrix[currentComputerIndex]
        for (j in neighbors.indices) {
            if (j != currentComputerIndex && neighbors[j] == 1 && !computersArray[j].isInfected) {
                computersArray[j].infect(probabilityGenerator)
            }
        }
    }

    fun attackComputers() {
        for (i in computersArray.indices) {
            if (computersArray[i].isInfected) {
                infectNeighbors(i)
            }
        }
    }
    fun startInfectingProcess(frequency: Long) {
        printStatistics(computersArray)
        while (!checkForTotalInfection(computersArray)) {
            attackComputers()
            infectionTurn += 1
            Thread.sleep(frequency)
            printStatistics(computersArray)
        }
    }

    fun getComputersArrayStatus(): BooleanArray {
        return computersArray.map { it.isInfected }.toBooleanArray()
    }
}
