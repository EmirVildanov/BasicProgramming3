package homework.hw3.task1

import homework.hw3.task1.carsStream.CarsStream
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class ParkingSimulation(
    maxParkingPlacesNumber: Int,
    private val parkingEntrancesNumber: Int,
    private val carStream: CarsStream
) {
    private val parking = Parking(maxParkingPlacesNumber)
    private var entranceMachines = List(parkingEntrancesNumber) {
        CarEntranceMachine(it, parking)
    }

    fun start() {
        println("At start: carStream isEmpty ${carStream.isEmpty}, parking isEmpty ${parking.isEmpty}")
//        runBlocking {
//            while () {
//                if () {
//                    launch {
//
//                    }
//                } else {
//                    launch {
//
//                    }
//                }
//            }
//        }
        while (!carStream.isEmpty || !parking.isEmpty) {
            GlobalScope.launch {
                println("Iteration")
                val entranceTurn = Random.nextBoolean() // true - enter new car, false - pop
                if (!carStream.isEmpty && entranceTurn) {
                    enterCar()
                } else {
                    popCar()
                }
            }
        }
        println("Simulation ended")
    }

    private suspend fun enterCar() {
        val currentCarPair = carStream.getNextCar()
        if (currentCarPair != null) {
            entranceMachines[currentCarPair.second].registerEnteringCar(currentCarPair.first)
        }
    }

    private suspend fun popCar() {
        val cars = parking.getCars()
        val poppingCarIndex = Random.nextInt(0, cars.size)
        val poppingCar = cars[poppingCarIndex]
        val leavingCarEntranceIndex = Random.nextInt(0, parkingEntrancesNumber)
        val currentCarPair = poppingCar to leavingCarEntranceIndex
        entranceMachines[currentCarPair.second].registerLeavingCar(currentCarPair.first)
    }
}
