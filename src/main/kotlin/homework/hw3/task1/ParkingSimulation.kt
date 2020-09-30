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
        var currentCarPair: Pair<Car, Int>?
        var counter = 0
        for (i in 0 until carStream.registeredCarsNumber) {
            val newCarPair = carStream.getNextCar()
            GlobalScope.launch {
                newCarPair?.first?.enterTheParking(parkingEntrances[newCarPair.second], carsWaitingDelayTime)
            }
        }
        while (carStream.registeredCarsNumber != parking.leavedCarNumber) {
//            println("Waiting")
//            println(parking.leavedCarNumber)
            continue
        }
        println("The end")
        return
//            while (!carStream.isEmpty || !parking.isEmpty()) {
//        while (counter < 10 && carStream.registeredCarsNumber != parking.leavedCarNumber) {
//            if (Random.nextBoolean()) {
//                currentCarPair = carStream.getNextCar()
//            } else {
//                continue
//            }
//            ++counter
////            currentCarPair = carStream.getNextCar()
//            println("Pair get in the main : $currentCarPair")
//            GlobalScope.launch {
//                val pairCopy = currentCarPair?.copy()
//                println("Copy is: $pairCopy")
//                if (pairCopy != null) {
//                    println("HERE AM I FUCK YOU")
//                    launch {
//                        enterCar(pairCopy, parkingEntrances)
//                    }
//                }
//            }
////                Thread.sleep(simulationDelayTime)
//        }
//        println("End of simulation")
    }
}
