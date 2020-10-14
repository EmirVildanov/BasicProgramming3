package homework.hw3.task1.server

import homework.hw3.task1.Car
import homework.hw3.task1.CarEntranceMachine
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

class Parking(
    private val maxParkingPlacesNumber: Int,
    private val entrancesNumber: Int
) {

    val parkingServer = ParkingAPI(this)
    var enteredCars = ConcurrentSkipListSet<Car>()
        private set
    var leavedCars = ConcurrentSkipListSet<Car>()
        private set
    private val carsOnParking = ConcurrentSkipListSet<Car>()
    private val carsOnParkingNumber = AtomicInteger(0)

    val entranceMachines = List(entrancesNumber) {
        CarEntranceMachine(it, parkingServer)
    }

    fun enterNewCar(car: Car): Boolean {
        return carsOnParkingNumber.getAndUpdate { value ->
            if (value < maxParkingPlacesNumber) {
                carsOnParking.add(car)
                enteredCars.add(car)
                return@getAndUpdate value + 1
            }
            return@getAndUpdate value
        } < maxParkingPlacesNumber
    }

    fun popCar(car: Car): Boolean {
        return carsOnParkingNumber.getAndUpdate { value ->
            if (value > 0) {
                carsOnParking.remove(car)
                leavedCars.add(car)
                return@getAndUpdate value - 1
            }
            return@getAndUpdate value
        } > 0
    }

    fun giveEntranceToLeave(): CarEntranceMachine {
        val leavingEntranceIndex = Random.nextInt(0, entrancesNumber)
        return entranceMachines[leavingEntranceIndex]
    }
}
