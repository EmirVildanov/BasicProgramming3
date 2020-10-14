package homework.hw3.task1.server

import homework.hw3.task1.Car
import homework.hw3.task1.CarEntranceMachine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ParkingAPI(private val parking: Parking) {

    suspend fun tryToEnter(car: Car): Boolean {
        return GlobalScope.async {
            parking.enterNewCar(car)
        }.await()
    }

    suspend fun tryToLeave(car: Car): Boolean {
        return GlobalScope.async {
            parking.popCar(car)
        }.await()
    }

    suspend fun giveEntranceToLeave(): CarEntranceMachine {
        return GlobalScope.async {
            parking.giveEntranceToLeave()
        }.await()
    }
}
