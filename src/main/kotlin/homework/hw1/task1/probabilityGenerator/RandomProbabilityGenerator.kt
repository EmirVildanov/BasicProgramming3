package homework.hw1.task1.probabilityGenerator

import kotlin.random.Random

class RandomProbabilityGenerator : ProbabilityGenerator {
    override fun generateProbability(): Double {
        return Random.nextDouble()
    }
}
