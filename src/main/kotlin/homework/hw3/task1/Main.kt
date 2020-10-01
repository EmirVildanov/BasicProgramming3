package homework.hw3.task1

import homework.hw3.task1.carsFileParser.CarsFileParser
import homework.hw3.task1.carsFileParser.FileReadException
import homework.hw3.task1.carsStream.CustomCarsStream
import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import java.io.File

fun main() {
    val maxParkingPlacesNumber: Int
    val parkingEntrancesNumber: Int
    try {
        print("Enter the max number of cars that can stay at parking at one time: ")
        maxParkingPlacesNumber = readLine()?.toInt() ?: 0
        print("Enter the parking entrance number(test value is 3): ")
        parkingEntrancesNumber = readLine()?.toInt() ?: 0
    } catch (exception: NumberFormatException) {
        println("Int number expected")
        return
    }
    val entranceCarsArray = mutableListOf<List<Car>>()
    for (i in 0 until parkingEntrancesNumber) {
        try {
            val carsArrayFile = File(
                "./src/main/resources/kotlin.homework.hw3.task1/CarsArrayOn${ i + 1 }Entrance.txt"
            )
            val carsFileParser = CarsFileParser(carsArrayFile)
            val carsArray = carsFileParser.getCarsArray()
            entranceCarsArray.add(carsArray)
        } catch (exception: FileReadException) {
            println(exception.message)
            println(exception.innerException.message)
            return
        }
    }
    val parkingSimulation = ParkingSimulation(
        maxParkingPlacesNumber,
        parkingEntrancesNumber,
        CustomCarsStream(entranceCarsArray.toList())
    )
    try {
        parkingSimulation.start()
    } catch (e: AlreadyRegisteredCarException) {
        println("Simulation found two same cars on the parking. Simulation killed")
    }
}
