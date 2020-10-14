package homework.hw3.task1

import homework.hw3.task1.server.ParkingAPI
import kotlinx.coroutines.delay

data class Car(private val carBrand: String, private val registrationSign: String) : Comparable<Car> {

    override fun toString(): String {
        return "$carBrand, $registrationSign"
    }

    suspend fun enterTheParking(
        parkingAPI: ParkingAPI,
        entranceMachine: CarEntranceMachine,
        delayTime: Long
    ) {
        entranceMachine.registerEnteringCar(this, delayTime)
        this.leaveParkingOnDelay(parkingAPI.giveEntranceToLeave(), delayTime)
    }

    private suspend fun leaveParkingOnDelay(entranceMachine: CarEntranceMachine, delayTime: Long) {
        delay(delayTime)
        entranceMachine.registerLeavingCar(this, delayTime)
    }

    override fun compareTo(other: Car): Int {
        return this.registrationSign.compareTo(other.registrationSign)
    }
}
