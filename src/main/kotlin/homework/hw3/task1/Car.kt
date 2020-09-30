package homework.hw3.task1

import kotlinx.coroutines.delay

data class Car(private val carBrand: String, private val registrationSign: String) {
    var onParking = false

    override fun toString(): String {
        return "$carBrand, $registrationSign"
    }

    suspend fun enterTheParking(entranceMachine: CarEntranceMachine, delayTime: Long) {
        while (!entranceMachine.registerEnteringCar(this)) {
            delay(delayTime)
            println("try to enter")
            continue
        }
        onParking = true
        this.leaveParkingOnDelay(entranceMachine, delayTime)
    }

    suspend fun leaveParkingOnDelay(entranceMachine: CarEntranceMachine, delayTime: Long) {
        delay(delayTime)
        while (!onParking) {
            println("$this try to leave")
            delay(delayTime)
            continue
        }
        entranceMachine.registerLeavingCar(this)
    }
}
