package homework.hw1.task1

import homework.hw1.task1.networlFileParser.exceptions.FileReadException
import homework.hw1.task1.networlFileParser.exceptions.NetworkMatrixException
import homework.hw1.task1.networlFileParser.NetworkFileParser
import homework.hw1.task1.probabilityGenerator.RandomProbabilityGenerator
import java.io.File

fun main() {
    lateinit var computersNetworkMatrix: List<List<Int>>
    lateinit var computersArray: List<Computer>
    var importCheck = true
    try {
        val dataFile = File("./src/main/resources/kotlin/homework/hw1/task1/" + "testFileForMain.txt")
        val computersDataParser = NetworkFileParser(dataFile)
        computersNetworkMatrix = computersDataParser.computersNetworkMatrix
        computersArray = computersDataParser.computersArray
    } catch (exception: FileReadException) {
        println(exception.message)
        println(exception.innerException.message)
        importCheck = false
    } catch (exception: NetworkMatrixException) {
        println(exception.message)
        importCheck = false
    }
    if (!importCheck) {
        return
    }
    val computersNetwork = ComputersNetwork(computersNetworkMatrix, computersArray, RandomProbabilityGenerator())
    print("Enter the infection turn frequency in milliseconds(for example, 1000): ")
    val frequency: Long
    try {
        frequency = readLine()?.toLong() ?: 0
    } catch (exception: NumberFormatException) {
        println("Int number expected")
        return
    }
    computersNetwork.startInfectingProcess(frequency)
}
