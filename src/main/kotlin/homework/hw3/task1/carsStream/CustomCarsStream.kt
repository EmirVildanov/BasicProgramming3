package homework.hw3.task1.carsStream

import homework.hw3.task1.Car
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random
import kotlin.system.exitProcess

class CustomCarsStream(private val entranceCarsArray: List<List<Car>>) : CarsStream {
    override var registeredCarsNumber = entranceCarsArray.flatten().size
    override var isEmpty = false

    private val carsArrayPointers = Array(entranceCarsArray.size) { 0 }

    override fun getNextCar(): Pair<Car, Int>? {
        val currentEntranceIndex: Int? = findFreeCarsArrayIndex()
        if (isEmpty || currentEntranceIndex == null) {
            if (currentEntranceIndex == null) {
                isEmpty = true
            }
            return null
        }
        val currentPointerValue = carsArrayPointers[currentEntranceIndex]
        val currentCar = entranceCarsArray[currentEntranceIndex][currentPointerValue]
        carsArrayPointers[currentEntranceIndex] += 1
        val file = File("./src/main/resources/kotlin.homework.hw3.task1/writeFile.txt\"")
        FileOutputStream(file, true).bufferedWriter().use { writer ->
            writer.append(currentCar.toString())
        }
        return currentCar to currentEntranceIndex
    }

    private fun findFreeCarsArrayIndex(): Int? {
        var answer: Int? = null
        val currentEntranceIndex: Int = Random.nextInt(0, entranceCarsArray.size)
        val currentPointerValue = carsArrayPointers[currentEntranceIndex]
        if (currentPointerValue < entranceCarsArray[currentEntranceIndex].size) {
            answer = currentEntranceIndex
        } else {
            for (i in entranceCarsArray.indices) {
                if (carsArrayPointers[i] < entranceCarsArray[i].size) {
                    answer = i
                }
            }
        }
        return answer
    }
}
