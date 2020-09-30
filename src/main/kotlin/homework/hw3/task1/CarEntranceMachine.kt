package homework.hw3.task1

class CarEntranceMachine(private val id: Int, private val parking: Parking) {
    fun registerEnteringCar(car: Car): Boolean {
        if (!parking.enterNewCar(car)) {
            println("The parking is full. You will have to wait a bit")
            return false
        }
        println("$car entered from entrance ${id + 1}")
        return true
    }

    fun registerLeavingCar(car: Car) {
        parking.popCar(car)
        println("$car leaved from entrance ${id + 1}")
    }
}
