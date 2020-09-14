package homework.hw1.task1

class Computer(val operationSystem: OperatingSystem, var isInfected: Boolean) {
    override fun toString(): String {
        if (isInfected) {
            return "${operationSystem.name} infected"
        }
        return "${operationSystem.name} clear"
    }
}
