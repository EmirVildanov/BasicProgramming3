package homework.hw3.task1.carsFileParser

import homework.hw3.task1.Car
import java.io.File
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.Scanner

class CarsFileParser(file: File) {
    private val scanner = Scanner(file)
    private lateinit var carsArray: List<Car>

    init {
        readCarsArrayFile()
    }

    fun getCarsArray() = carsArray

    private fun readCarsArrayFile() {
        try {
            val carsNumber = getCarsNumber()
            carsArray = getCarsArray(carsNumber)
        } catch (exception: NumberFormatException) {
            handleException(exception)
        } catch (exception: IllegalArgumentException) {
            handleException(exception)
        } catch (exception: NoSuchElementException) {
            handleException(exception)
        }
    }

    private fun getCarsNumber(): Int {
        val carsNumber = scanner.nextLine().toInt()
        require(carsNumber > 0) {
            "The number of cars should be positive"
        }
        return carsNumber
    }

    private fun getCarsArray(carsNumber: Int): List<Car> {
        val carsArray = mutableListOf<Car>()
        for (i in 0 until carsNumber) {
            val currentCarDataString = scanner.nextLine()
            require(currentCarDataString.matches("\\w+, \\w+".toRegex())) {
                "Wrong current car info $currentCarDataString"
            }
            val currentComputerData = currentCarDataString.split(", ")
            val carBrand = currentComputerData[0]
            val carRegistrationSign = currentComputerData[1]
            carsArray.add(Car(carBrand, carRegistrationSign))
        }
        return carsArray
    }

    private fun handleException(innerException: Exception) {
        throw(FileReadException(
            "Problem occurred while reading file",
            innerException
        ))
    }
}
