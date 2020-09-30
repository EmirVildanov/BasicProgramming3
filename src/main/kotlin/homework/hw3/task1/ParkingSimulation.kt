package homework.hw3.task1

import homework.hw3.task1.carsStream.CarsStream
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.Job
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ParkingSimulation(
    maxParkingPlacesNumber: Int,
    parkingEntrancesNumber: Int,
    private val carStream: CarsStream,
    private val carsWaitingDelayTime: Long = 500
) {
    private val parking = Parking(maxParkingPlacesNumber, parkingEntrancesNumber)

    fun start() {
        val parkingEntrances = parking.entranceMachines
        val threadsTracker = mutableListOf<Job>()
        for (i in 0 until carStream.registeredCarsNumber) {
            val newCarPair = carStream.getNextCar()
            val newThread = GlobalScope.launch {
                newCarPair?.first?.enterTheParking(parkingEntrances[newCarPair.second], carsWaitingDelayTime)
            }
            threadsTracker.add(newThread)
        }
        runBlocking {
            threadsTracker.forEach {
                it.join()
            }
        }
        println("Simulation ended")
    }
}
