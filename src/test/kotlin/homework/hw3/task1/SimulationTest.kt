package homework.hw3.task1

import homework.hw1.task1.networkFileParser.NetworkFileParser
import homework.hw1.task1.networkFileParser.exceptions.NetworkMatrixException
import homework.hw3.task1.carsFileParser.CarsFileParser
import homework.hw3.task1.carsStream.CustomCarsStream
import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {

    private val resourcesPath = "./src/test/resources/kotlin.homework.hw3.task1/simulation/"

    @Test
    fun shouldThrowExceptionBecauseOfWrongCarsDataThatConsistsTwoSameCars() {
        val testFile = File(resourcesPath + "testFileWithUncoveredNetwork.txt")
        val carsDataParser = CarsFileParser(testFile)
        val carsArray = carsDataParser.getCarsArray()
        val entranceCarsArray = mutableListOf<List<Car>>()
        entranceCarsArray.add(carsArray)
        val parkingSimulation = ParkingSimulation(
            2,
            2,
            CustomCarsStream(entranceCarsArray.toList())
        )
        Assertions.assertThrows(AlreadyRegisteredCarException::class.java) {
            parkingSimulation.start()
        }
    }

    @Test
    fun shouldNotAllowCarToEnterBecauseOfFullParking() {

    }

    @Test
    fun shouldEnterCar() {

    }
}
