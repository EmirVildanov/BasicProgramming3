package homework.hw3.task1

import homework.hw1.task1.networkFileParser.NetworkFileParser
import homework.hw1.task1.networkFileParser.exceptions.NetworkMatrixException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {

    private val resourcesPath = "./src/test/resources/kotlin.homework.hw3.task1/simulation/"

    @Test
    fun shouldThrowExceptionBecauseOfWrongCarsDataThatConsistsTwoSameCars() {
        val testFile = File(resourcesPath + "testFileWithUncoveredNetwork.txt")
        Assertions.assertThrows(NetworkMatrixException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldNotAllowCarToEnterBecauseOfFullParking() {

    }

    @Test
    fun shouldEnterCar() {

    }
}
