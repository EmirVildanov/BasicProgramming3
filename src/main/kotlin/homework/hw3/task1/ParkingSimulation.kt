package homework.hw3.task1

import homework.hw3.task1.carsStream.CarsStream
import homework.hw3.task1.exceptions.AlreadyRegisteredCarException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

class ParkingSimulation(
    maxParkingPlacesNumber: Int,
    parkingEntrancesNumber: Int,
    private val carStream: CarsStream,
    private val carsWaitingDelayTime: Long = 500
) {
    private val parking = Parking(maxParkingPlacesNumber, parkingEntrancesNumber)

    fun start() = runBlocking {
        val parkingEntrances = parking.entranceMachines
        val threadsTracker = mutableListOf<Deferred<Unit?>>()
        for (i in 0 until carStream.registeredCarsNumber) {
            val newCarPair = carStream.getNextCar()
            val newThread = GlobalScope.async {
                newCarPair?.first?.enterTheParking(parking, parkingEntrances[newCarPair.second], carsWaitingDelayTime)
            }
            threadsTracker.add(newThread)
        }
        try {
            threadsTracker.awaitAll()
//            threadsTracker.forEach {
//                it.await()
//            }
        } catch (exception: AlreadyRegisteredCarException) {
            throw exception
        }
//        while (parking.enteredCarsNumber != parking.leavedCarsNumber) {
//            continue
//        }
        println("enteredCars:")
        parking.enteredCars.forEach {
            println(it)
        }
        println("leavedCars:")
        parking.leavedCars.forEach {
            println(it)
        }
        println("Simulation ended successfully")
    }
    fun getParkingEnteredCarsNumber() = parking.enteredCars.size
    fun getParkingLeavedCarsNumber() = parking.leavedCars.size
}
