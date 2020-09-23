package homework.hw3.task1

import homework.hw3.task1.carsFileParser.CarsFileParser
import homework.hw3.task1.carsFileParser.FileReadException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class CarsFileParserTest {

    private val resourcesPath = "./src/test/resources/kotlin.homework.hw3.task1/parser/"

    @Test
    fun shouldThrowExceptionBecauseOfEmptyCarsNumberLine() {
        val testFile = File(resourcesPath + "testFileThatConsistWrongComputerLinesNumber.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val carsFileParser =
                CarsFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfWrongCarsLinesNumber() {
        val testFile = File(resourcesPath + "testFileThatConsistWrongComputerLinesNumber.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val carsFileParser =
                CarsFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfWrongCarDataFormat() {
        val testFile = File(resourcesPath + "testFileThatConsistWrongComputerLinieInfo.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val carsFileParser =
                CarsFileParser(testFile)
        }
    }
}
