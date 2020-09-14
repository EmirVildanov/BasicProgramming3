package homework.hw1.task1

import homework.hw1.task1.exceptions.NetworkMatrixException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.io.File

internal class MainKtTest {
    private val resourcesPath = "./src/test/resources/kotlin.homework.hw1.task1/"
    private val testInfectingProbability = 1.0

    @Test
    fun shouldThrowExceptionBecauseOfUncoveredNetwork() {
        val testFile = File(resourcesPath + "testFileWithUncoveredNetwork.txt")
        assertThrows(NetworkMatrixException::class.java) {
            val computersDataParser = NetworkFileParser(testFile)
        }
    }

    @Test
    fun shouldTestInfectionProcessOnTwoComputers() {
        val testFile = File(resourcesPath + "testDataWithTwoComputers.txt")
        val computersDataParser = NetworkFileParser(testFile)
        val computersNetworkMatrix = computersDataParser.computersNetworkMatrix
        val computersArray = computersDataParser.computersArray
        val computersNetwork = ComputersNetwork(computersNetworkMatrix, computersArray)
        assertArrayEquals(booleanArrayOf(false, true), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers(testInfectingProbability)
        assertArrayEquals(booleanArrayOf(true, true), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers(testInfectingProbability)
    }
}
