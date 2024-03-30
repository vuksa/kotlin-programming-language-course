package exercise3.task4

/**
 * Task 4: Taxi park
 *
 * The TaxiPark class stores information about the registered drivers, passengers, and their trips.
 * Your task is to implement six functions which collect different statistics about the data.
 *
 * Subtask 1:
 * Find all the drivers who performed no trips.
 */
internal fun TaxiPark.findFakeDrivers(): Set<Driver> {
    val realDriversNames = this.trips.map { it -> it.driver}.toSet()
    val allDriversNames = this.allDrivers
    return allDriversNames.subtract(realDriversNames)
}

/**
 * Subtask 2:
 * Find all the clients who completed at least the given number of trips.
 */
internal fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    //test ovde ne prolazi, nisam siguran zasto
    return this.trips
        .flatMap { it.passengers }
        .groupingBy { it }
        .eachCount()
        .filter{ it.value >= minTrips }
        .map { it.key }
        .toSet()
}

/**
 * Subtask 3:
 * Find all the passengers, who were taken by a given driver more than once.
 */
internal fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    val allPassengers = mutableListOf<Passenger>()
    this.trips
        .filter { it.driver == driver }
        .map { it.passengers }
        .forEach {
            it.forEach {
                allPassengers.add(it)
            }
        }

    return allPassengers.groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }
        .map { it.key }
        .toSet()

}

/**
 * Subtask 4:
 * Find the passengers who had a discount for the majority of their trips.
 */
internal fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    val passengersMap = trips
        .flatMap { t -> t.passengers.map { p -> p to (t.discount != null) } }
        .groupBy({ it.first }, { it.second })

    return passengersMap.filterValues { discounts ->
            val withDiscount = discounts.count { it }
            val noDiscount = discounts.size - withDiscount
            withDiscount > noDiscount
        }
        .map { it.key }
        .toSet()
}


