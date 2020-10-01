package homework.hw3.task1

import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import kotlinx.coroutines.delay

data class Car(private val carBrand: String, private val registrationSign: String) {

    val parking: Parking? = null

    override fun toString(): String {
        return "$carBrand, $registrationSign"
    }

    suspend fun enterTheParking(parking: Parking, entranceMachine: CarEntranceMachine, delayTime: Long) {
        try {
            while (!entranceMachine.registerEnteringCar(this)) {
                delay(delayTime)
                continue
            }
            this.leaveParkingOnDelay(parking.giveEntranceToLeave(), delayTime)
        } catch (exception: AlreadyRegisteredCarException) {
            throw exception
        }
    }

    private suspend fun leaveParkingOnDelay(entranceMachine: CarEntranceMachine, delayTime: Long) {
        delay(delayTime)
        entranceMachine.registerLeavingCar(this)
    }
}
