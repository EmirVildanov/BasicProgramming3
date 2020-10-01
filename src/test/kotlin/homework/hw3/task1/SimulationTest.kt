package homework.hw3.task1

import homework.hw3.task1.carsFileParser.CarsFileParser
import homework.hw3.task1.carsStream.CustomCarsStream
import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import org.junit.jupiter.api.Assertions
// import org.junit.jupiter.api.Assertions.assertEquals
// import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import java.io.File

internal class SimulationTest {

    private val resourcesPath = "./src/test/resources/kotlin.homework.hw3.task1/simulation/"

    @Test
    fun shouldThrowExceptionBecauseOfWrongCarsDataThatConsistsTwoSameCars() {
        val parkingEntrancesNumber = 2
        val entranceCarsArray = mutableListOf<List<Car>>()
        for (i in 0 until parkingEntrancesNumber) {
            val carsArrayFile = File(
                resourcesPath + "exception/carsArrayOn${ i + 1 }Entrance.txt"
            )
            val carsFileParser = CarsFileParser(carsArrayFile)
            val carsArray = carsFileParser.getCarsArray()
            entranceCarsArray.add(carsArray)
        }

        val parkingSimulation = ParkingSimulation(
            2,
            2,
            CustomCarsStream(entranceCarsArray.toList())
        )
        Assertions.assertThrows(AlreadyRegisteredCarException::class.java) {
            parkingSimulation.start()
        }
    }

//    @RepeatedTest(5)
//    fun shouldCheckThatAllCarsEnteredAndLeavedParking() {
//        val parkingEntrancesNumber = 2
//        val entranceCarsArray = mutableListOf<List<Car>>()
//        for (i in 0 until parkingEntrancesNumber) {
//            val carsArrayFile = File(
//                resourcesPath + "test1/carsArrayOn${ i + 1 }Entrance.txt"
//            )
//            val carsFileParser = CarsFileParser(carsArrayFile)
//            val carsArray = carsFileParser.getCarsArray()
//            entranceCarsArray.add(carsArray)
//        }
//
//        val parkingSimulation = ParkingSimulation(
//            2,
//            2,
//            CustomCarsStream(entranceCarsArray.toList())
//        )
//        parkingSimulation.start()
//        assertEquals(parkingSimulation.getParkingEnteredCarsNumber(), parkingSimulation.getParkingLeavedCarsNumber())
//    }
}
