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
    return this.allDrivers subtract this.trips.map { it.driver }.toSet()
}

/**
 * Subtask 2:
 * Find all the clients who completed at least the given number of trips.
 */
internal fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    return this.trips.
                flatMap { it.passengers }.
                groupingBy { it }.eachCount().
                filter { it.value >= minTrips }.keys
}
/**
 * Subtask 3:
 * Find all the passengers, who were taken by a given driver more than once.
 */
internal fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    return this.trips.filter{ it.driver == driver}.
                flatMap { it.passengers }.
                groupingBy { it }.eachCount().
                filter { it.value > 1 }.keys
}

/**
 * Subtask 4:
 * Find the passengers who had a discount for the majority of their trips.
 */
internal fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    return this.allPassengers.filter { passenger ->
        val tripsForPassenger = trips.filter { it.passengers.contains(passenger) }
        val tripsWithDiscount = tripsForPassenger.count { it.discount != null && it.discount > 0.0 }
        tripsWithDiscount > tripsForPassenger.size / 2
    }.toSet()
}
