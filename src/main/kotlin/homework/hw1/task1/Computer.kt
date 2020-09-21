package homework.hw1.task1

import homework.hw1.task1.probabilityGenerator.ProbabilityGenerator

class Computer(
    private val operationSystem: OperatingSystem,
    var isInfected: Boolean
) {
    override fun toString(): String {
        if (isInfected) {
            return "${operationSystem.name} infected"
        }
        return "${operationSystem.name} clear"
    }

    fun infect(probabilityGenerator: ProbabilityGenerator) {
        val infectionProbability = probabilityGenerator.generateProbability()
        if (operationSystem.infectingEdge < infectionProbability) {
            isInfected = true
        }
    }
}
