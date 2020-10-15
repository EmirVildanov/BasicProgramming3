package homework.hw3.task1

import homework.hw3.task1.carsStream.CarsStream
import homework.hw3.task1.server.Parking
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll
import java.lang.IllegalArgumentException

class Simulation(
    maxParkingPlacesNumber: Int,
    parkingEntrancesNumber: Int,
    private val carStream: CarsStream,
    private val carsWaitingDelayTime: Long = 500
) {
    private val parking =
        Parking(maxParkingPlacesNumber, parkingEntrancesNumber)

    fun start() = runBlocking {
        val parkingEntrances = parking.entranceMachines
        val coroutinesTracker = mutableListOf<Deferred<Unit?>>()
        val gettingCars = mutableListOf<Pair<Car, Int>>()
        for (i in 0 until carStream.registeredCarsNumber) {
            val newCarPair = carStream.getNextCar()
            if (!gettingCars.contains(newCarPair)) {
                gettingCars.add(newCarPair)
            } else {
                throw IllegalArgumentException("Stream gave two same cars")
            }
            val newCoroutine = GlobalScope.async {
                newCarPair.first.enterTheParking(
                    parking.parkingServer,
                    parkingEntrances[newCarPair.second],
                    carsWaitingDelayTime
                )
            }
            coroutinesTracker.add(newCoroutine)
        }
        coroutinesTracker.awaitAll()
        println("Simulation ended successfully")
    }
    fun getParkingEnteredCarsNumber() = parking.enteredCars.size
    fun getParkingLeavedCarsNumber() = parking.leavedCars.size
}
