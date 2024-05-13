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
    return allDrivers.filter { driver -> trips.none { it.driver == driver } }
        .toSet()
}

/**
 * Subtask 2:
 * Find all the clients who completed at least the given number of trips.
 */
internal fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {
    val passengerTripsCount = trips
        .flatMap { it.passengers }
        .groupingBy { it }
        .eachCount()

    return passengerTripsCount
        .filter { it.value >= minTrips }
        .keys}

/**
 * Subtask 3:
 * Find all the passengers, who were taken by a given driver more than once.
 */
internal fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    val frequentPassengers = trips
        .filter { it.driver == driver }
        .flatMap { it.passengers }
        .groupingBy { it }
        .eachCount()
        .filter { it.value > 1 }

    return frequentPassengers.keys
}

/**
 * Subtask 4:
 * Find the passengers who had a discount for the majority of their trips.
 */
internal fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    val passengerDiscountTripsCount = trips
        .flatMap { trip ->
            trip.passengers.map { passenger ->
                passenger to (if (trip.discount != null && trip.discount > 0) 1 else 0)
            }
        }
        .groupBy({ it.first }, { it.second })
        .mapValues { (_, discounts) -> discounts.sum() }

    return passengerDiscountTripsCount
        .filter { (_, discounts) ->
            discounts >= trips.size / 2
        }
        .keys
}
