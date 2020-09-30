package homework.hw3.task1

import kotlinx.coroutines.delay

data class Car(private val carBrand: String, private val registrationSign: String) {
    override fun toString(): String {
        return "$carBrand, $registrationSign"
    }

    suspend fun enterTheParking(entranceMachine: CarEntranceMachine, delayTime: Long) {
        while (!entranceMachine.registerEnteringCar(this)) {
            delay(delayTime)
            continue
        }
    }

    suspend fun leaveParkingOnDelay(entranceMachine: CarEntranceMachine, delayTime: Long) {
        delay(delayTime)
        entranceMachine.registerLeavingCar(this)
    }
}
