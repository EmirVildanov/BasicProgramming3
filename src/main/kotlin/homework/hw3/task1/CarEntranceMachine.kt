package homework.hw3.task1

class CarEntranceMachine(private val id: Int, private val parking: Parking) {
    fun registerEnteringCar(car: Car) {
        while (!parking.enterNewCar(car)) {
            continue
        }
        println("$car entered from entrance $id")
    }

    fun registerLeavingCar(car: Car) {
        parking.popCar(car)
        println("$car leaved from entrance $id")
    }
}
