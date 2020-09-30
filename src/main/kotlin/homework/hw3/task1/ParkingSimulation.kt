package homework.hw3.task1

import homework.hw3.task1.carsStream.CarsStream
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.exitProcess

class ParkingSimulation(
    maxParkingPlacesNumber: Int,
    parkingEntrancesNumber: Int,
    private val carStream: CarsStream,
    private val carsWaitingDelayTime: Long = 500,
    private val simulationDelayTime: Long = 100
) {
    private val parking = Parking(maxParkingPlacesNumber, parkingEntrancesNumber)

    fun start() {
        val parkingEntrances = parking.entranceMachines
        runBlocking {
            for (i in 0 until carStream.registeredCarsNumber) {
                val newCarPair = carStream.getNextCar()
                GlobalScope.launch {
                    newCarPair?.first?.enterTheParking(parkingEntrances[newCarPair.second], carsWaitingDelayTime)
                }
            }
        }
        println("The end")
    }
}
