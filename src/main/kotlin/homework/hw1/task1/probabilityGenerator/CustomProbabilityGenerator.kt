package homework.hw1.task1.probabilityGenerator

import homework.hw1.task1.probabilityGenerator.exceptions.OutOfProbabilityArrayBoundsException

class CustomProbabilityGenerator(private val probabilitiesArray: DoubleArray) :
    ProbabilityGenerator {
    private var currentProbabilityIndex = 0
    override fun generateProbability(): Double {
        if (currentProbabilityIndex >= probabilitiesArray.size) {
            throw OutOfProbabilityArrayBoundsException("Array of probabilities is too small")
        }
        val currentProbability = probabilitiesArray[currentProbabilityIndex]
        ++currentProbabilityIndex
        return currentProbability
    }
}
