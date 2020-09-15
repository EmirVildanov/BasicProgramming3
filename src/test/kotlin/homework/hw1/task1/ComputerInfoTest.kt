package homework.hw1.task1

import homework.hw1.task1.networlFileParser.NetworkFileParser
import homework.hw1.task1.probabilityGenerator.CustomProbabilityGenerator
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertArrayEquals
import java.io.File

internal class ComputerInfoTest {
    private val resourcesPath = "./src/test/resources/kotlin.homework.hw1.task1/network/"
    private val testProbabilitiesArray = doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0)

    @Test
    fun shouldTestInfectionProcessOn2Computers() {
        val testFile = File(resourcesPath + "testFileWith2Computers.txt")
        val computersDataParser = NetworkFileParser(testFile)
        val computersNetworkMatrix = computersDataParser.computersNetworkMatrix
        val computersArray = computersDataParser.computersArray
        val testProbabilitiesArray = doubleArrayOf(1.0)
        val probabilityGenerator = CustomProbabilityGenerator(testProbabilitiesArray)
        val computersNetwork = ComputersNetwork(computersNetworkMatrix, computersArray, probabilityGenerator)
        assertArrayEquals(booleanArrayOf(false, true), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers()
        assertArrayEquals(booleanArrayOf(true, true), computersNetwork.getComputersArrayStatus())
    }

    @Test
    fun shouldTestInfectionProcessOn3Computers() {
        val testFile = File(resourcesPath + "testFileWith3Computers.txt")
        val computersDataParser = NetworkFileParser(testFile)
        val computersNetworkMatrix = computersDataParser.computersNetworkMatrix
        val computersArray = computersDataParser.computersArray
        val testProbabilitiesArray = doubleArrayOf(0.5, 0.5, 0.9)
        val probabilityGenerator = CustomProbabilityGenerator(testProbabilitiesArray)
        val computersNetwork = ComputersNetwork(computersNetworkMatrix, computersArray, probabilityGenerator)
        assertArrayEquals(booleanArrayOf(false, true, false), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers()
        assertArrayEquals(booleanArrayOf(false, true, true), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers()
        assertArrayEquals(booleanArrayOf(true, true, true), computersNetwork.getComputersArrayStatus())
    }

    @Test
    fun shouldTestSituationWhenComputerWillNotBeInfectedBecauseOfHighInfectingEdge() {
        val testFile = File(resourcesPath + "testFileWith2ComputersToTestNotInfectingSituation.txt")
        val computersDataParser = NetworkFileParser(testFile)
        val computersNetworkMatrix = computersDataParser.computersNetworkMatrix
        val computersArray = computersDataParser.computersArray
        val testProbabilitiesArray = doubleArrayOf(0.1)
        val probabilityGenerator = CustomProbabilityGenerator(testProbabilitiesArray)
        val computersNetwork = ComputersNetwork(computersNetworkMatrix, computersArray, probabilityGenerator)
        assertArrayEquals(booleanArrayOf(false, true), computersNetwork.getComputersArrayStatus())
        computersNetwork.attackComputers()
        assertArrayEquals(booleanArrayOf(false, true), computersNetwork.getComputersArrayStatus())
    }
}
