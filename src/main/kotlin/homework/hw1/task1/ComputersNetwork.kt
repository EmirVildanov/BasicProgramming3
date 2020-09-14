package homework.hw1.task1

import kotlin.random.Random

class ComputersNetwork(
    private val computersNetworkMatrix: List<List<Int>>,
    private val computersArray: List<Computer>
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

    private fun infectNeighbors(
        currentComputerIndex: Int,
        neighbors: List<Int>,
        infectingProbability: Double?
    ) {
        for (j in neighbors.indices) {
            if (j != currentComputerIndex && neighbors[j] == 1 && !computersArray[j].isInfected) {
                val generatedInfectingProbability: Double
                if (infectingProbability != null) {
                    generatedInfectingProbability = infectingProbability
                } else {
                    generatedInfectingProbability = Random.nextDouble()
                }
                if (computersArray[j].operationSystem.infectingEdge < generatedInfectingProbability) {
                    computersArray[j].isInfected = true
                }
            }
        }
    }

    fun attackComputers() {
        for (i in computersArray.indices) {
            if (computersArray[i].isInfected) {
                val neighbors = computersNetworkMatrix[i]
                infectNeighbors(i, neighbors, null)
            }
        }
    }

    fun attackComputers(infectingProbability: Double) {
        for (i in computersArray.indices) {
            if (computersArray[i].isInfected) {
                val neighbors = computersNetworkMatrix[i]
                infectNeighbors(i, neighbors, infectingProbability)
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
