package homework.hw3.task1

import homework.hw3.task1.carsFileParser.CarsFileParser
import homework.hw3.task1.carsStream.CustomCarsStream
 import org.junit.jupiter.api.Assertions.assertEquals
 import org.junit.jupiter.api.RepeatedTest
import java.io.File

internal class SimulationTest {

    private val resourcesPath = "./src/test/resources/kotlin.homework.hw3.task1/simulation/"

    @RepeatedTest(5)
    fun shouldCheckThatAllCarsEnteredAndLeavedParkingWithOnly2AvailablePlaces() {
        val parkingEntrancesNumber = 2
        val entranceCarsArray = mutableListOf<List<Car>>()
        for (i in 0 until parkingEntrancesNumber) {
            val carsArrayFile = File(
                resourcesPath + "test1/carsArrayOn${i + 1}Entrance.txt"
            )
            val carsFileParser = CarsFileParser(carsArrayFile)
            val carsArray = carsFileParser.getCarsArray()
            entranceCarsArray.add(carsArray)
        }

        val parkingSimulation = Simulation(
            2,
            2,
            CustomCarsStream(entranceCarsArray.toList())
        )
        parkingSimulation.start()
        assertEquals(parkingSimulation.getParkingEnteredCarsNumber(), parkingSimulation.getParkingLeavedCarsNumber())
    }

    @RepeatedTest(5)
    fun shouldCheckAllCarsEnteredAndLeavedOnParkingWith100AvailablePlaces() {
        val parkingEntrancesNumber = 2
        val entranceCarsArray = mutableListOf<List<Car>>()
        for (i in 0 until parkingEntrancesNumber) {
            val carsArrayFile = File(
                resourcesPath + "test1/carsArrayOn${i + 1}Entrance.txt"
            )
            val carsFileParser = CarsFileParser(carsArrayFile)
            val carsArray = carsFileParser.getCarsArray()
            entranceCarsArray.add(carsArray)
        }

        val parkingSimulation = Simulation(
            100,
            2,
            CustomCarsStream(entranceCarsArray.toList())
        )
        parkingSimulation.start()
        assertEquals(parkingSimulation.getParkingEnteredCarsNumber(), parkingSimulation.getParkingLeavedCarsNumber())
    }

    @RepeatedTest(5)
    fun shouldCheckAllCarsEnteredAndLeavedOnParkingWithThreeEntrances() {
        val parkingEntrancesNumber = 2
        val entranceCarsArray = mutableListOf<List<Car>>()
        for (i in 0 until parkingEntrancesNumber) {
            val carsArrayFile = File(
                resourcesPath + "test1/carsArrayOn${i + 1}Entrance.txt"
            )
            val carsFileParser = CarsFileParser(carsArrayFile)
            val carsArray = carsFileParser.getCarsArray()
            entranceCarsArray.add(carsArray)
        }

        val parkingSimulation = Simulation(
            2,
            3,
            CustomCarsStream(entranceCarsArray.toList())
        )
        parkingSimulation.start()
        assertEquals(parkingSimulation.getParkingEnteredCarsNumber(), parkingSimulation.getParkingLeavedCarsNumber())
    }
}
