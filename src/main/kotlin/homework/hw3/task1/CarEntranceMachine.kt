package homework.hw3.task1

import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import homework.hw3.task1.exceptions.ParkingIsOverflowed

class CarEntranceMachine(
    private val id: Int,
    private val parking: Parking
) {
    fun registerEnteringCar(car: Car): Boolean {
        try {
            var isCarEntered = true
            if (!parking.enterNewCar(car)) {
//                println("The parking is full. You will have to wait a bit")
                isCarEntered = false
            }
            if (isCarEntered) {
                println("$car entered from entrance ${id + 1}")
            }
            return isCarEntered
        } catch (exception: AlreadyRegisteredCarException) {
            throw exception
        } catch (exception: ParkingIsOverflowed) {
            return false
        }
    }

    fun registerLeavingCar(car: Car) {
        parking.popCar(car)
        println("$car leaved from entrance ${id + 1}")
    }
}
