package homework.hw3.task1.carsStream

import homework.hw3.task1.Car

interface CarsStream {
    var registeredCarsNumber: Int
    var isEmpty: Boolean
    fun getNextCar(): Pair<Car, Int>
}
