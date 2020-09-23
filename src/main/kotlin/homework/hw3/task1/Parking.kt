package homework.hw3.task1

import homework.hw3.task1.exceptions.AlreadyRegisteredCarException

class Parking(
    private val maxParkingPlacesNumber: Int
) {
    private val carsOnParking = mutableListOf<Car>()

    var isEmpty = carsOnParking.isEmpty()

    fun getCars() = carsOnParking

    fun enterNewCar(car: Car): Boolean {
        if (carsOnParking.contains(car)) {
            throw AlreadyRegisteredCarException("Two cars with the same registration signs appeared")
        }
        if (carsOnParking.size < maxParkingPlacesNumber) {
            carsOnParking.add(car)
            return true
        }
        print("Sorry. The parking is full. You have to wait a little")
        return false
    }

    fun popCar(car: Car): Car {
        carsOnParking.remove(car)
        return car
    }
}
