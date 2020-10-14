package homework.hw3.task1

import homework.hw3.task1.server.ParkingAPI
import kotlinx.coroutines.delay

class CarEntranceMachine(
    private val id: Int,
    private val parkingAPI: ParkingAPI
) {
    suspend fun registerEnteringCar(car: Car, delayTime: Long) {
        while (!parkingAPI.tryToEnter(car)) {
            delay(delayTime)
            println("The parking is full. $car is waiting for opportunity to enter")
        }
        println("$car entered from entrance ${id + 1}")
    }

    suspend fun registerLeavingCar(car: Car, delayTime: Long) {
        while (!parkingAPI.tryToLeave(car)) {
            delay(delayTime)
            println("$car tries to leave. Waiting for an opportunity to leave")
        }
        println("$car leaved from entrance ${id + 1}")
    }
}
