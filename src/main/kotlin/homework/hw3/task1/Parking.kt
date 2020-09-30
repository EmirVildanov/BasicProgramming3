package homework.hw3.task1

import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class Parking(
    private val maxParkingPlacesNumber: Int,
    private val entrancesNumber: Int,
    private val carLeavingDelayTime: Long = 500
) {
    private val carsOnParking = mutableListOf<Car>()
    val entranceMachines = List(entrancesNumber) {
        CarEntranceMachine(it, this)
    }

    var leavedCarNumber = 0
        private set
    fun isEmpty() = carsOnParking.isEmpty()
    fun isAvailable() = carsOnParking.size < maxParkingPlacesNumber

    fun enterNewCar(car: Car): Boolean {
        if (carsOnParking.contains(car)) {
            throw AlreadyRegisteredCarException("Two cars with the same registration signs appeared" +
            "\n $car")
        }
        if (carsOnParking.size < maxParkingPlacesNumber) {
            carsOnParking.add(car)
            val leavingEntranceIndex = Random.nextInt(0, entrancesNumber)
            GlobalScope.launch {
                car.leaveParkingOnDelay(entranceMachines[leavingEntranceIndex], carLeavingDelayTime)
            }
            return true
        }
        return false
    }

    fun popCar(car: Car) {
        leavedCarNumber++
        carsOnParking.remove(car)
        println("The number of leaved cars is : $leavedCarNumber")
    }
}
