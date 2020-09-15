package homework.hw1.task1

import homework.hw1.task1.networlFileParser.exceptions.FileReadException
import homework.hw1.task1.networlFileParser.exceptions.NetworkMatrixException
import homework.hw1.task1.networlFileParser.NetworkFileParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class NetworkFileParserTest {
    private val resourcesPath = "./src/test/resources/kotlin.homework.hw1.task1/parser/"

    @Test
    fun shouldThrowExceptionBecauseOfUncoveredNetwork() {
        val testFile = File(resourcesPath + "testFileWithUncoveredNetwork.txt")
        Assertions.assertThrows(NetworkMatrixException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfMatrixValueInWrongPosition() {
        val testFile = File(resourcesPath + "testFileWithMatrixValueInWrongPosition.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfWrongInfectingEdgeInfo() {
        val testFile = File(resourcesPath + "testFleWithWrongInfectingEdgeArgument.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfWrongIsInfectedFormat() {
        val testFile = File(resourcesPath + "testFileWithWrongIsInfectedFormat.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfWrongComputerInfoFormat() {
        val testFile = File(resourcesPath + "testFileWithWrongComputerInfoFormat.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfEmptyStringOfComputerInfoInReadingFile() {
        val testFile = File(resourcesPath + "testFileWithEmptyStringOfComputerInfo.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfEmptyStringOfMatrixInfoInReadingFile() {
        val testFile = File(resourcesPath + "testFileWithEmptyStringOfMatrixInfo.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldThrowExceptionBecauseOfEmptyStringOfComputersNumberInReadingFile() {
        val testFile = File(resourcesPath + "testFileWithEmptyStringOfComputerNumbers.txt")
        Assertions.assertThrows(FileReadException::class.java) {
            val computersDataParser =
                NetworkFileParser(testFile)
        }
    }
}
