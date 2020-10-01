package homework.hw3.task1

import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import homework.hw3.task1.exceptions.ParkingIsOverflowed
import kotlin.random.Random

class Parking(
    private val maxParkingPlacesNumber: Int,
    private val entrancesNumber: Int
) {
    var enteredCars = mutableListOf<Car>()
        private set
    var leavedCars = mutableListOf<Car>()
        private set
//    var carsCurrentlyOnParkingNumber = 0
    private val carsOnParking = mutableListOf<Car>()
    val entranceMachines = List(entrancesNumber) {
        CarEntranceMachine(it, this)
    }
    fun enterNewCar(car: Car): Boolean {
        if (carsOnParking.contains(car)) {
            throw AlreadyRegisteredCarException("Two cars with the same registration signs appeared" +
            "\n $car")
        }
        if (carsOnParking.size < maxParkingPlacesNumber) {
            carsOnParking.add(car)
            if (carsOnParking.size > maxParkingPlacesNumber) {
                carsOnParking.remove(car)
                throw ParkingIsOverflowed("Not enough space for entering a car")
            }
            enteredCars.add(car)
            return true
        }
        return false
    }

    fun popCar(car: Car) {
        carsOnParking.remove(car)
        leavedCars.add(car)
    }

    fun giveEntranceToLeave(): CarEntranceMachine {
        val leavingEntranceIndex = Random.nextInt(0, entrancesNumber)
        return entranceMachines[leavingEntranceIndex]
    }
}
