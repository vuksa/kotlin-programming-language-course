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
internal fun TaxiPark.findFakeDrivers(): Set<Driver> = allDrivers
    .subtract(trips.map { it.driver }.toSet())

/**
 * Subtask 2:
 * Find all the clients who completed at least the given number of trips.
 */
internal fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
    trips
        .flatMap { it.passengers }
        .groupingBy { passenger -> passenger }
        .eachCount()
        .filter { (_, numOfTrips) -> numOfTrips >= minTrips }
        .map { it.key }
        .toSet()

/**
 * Subtask 3:
 * Find all the passengers, who were taken by a given driver more than once.
 */
internal fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = trips
    .filter { trip -> trip.driver == driver }
    .flatMap { trip -> trip.passengers }
    .groupingBy { passenger -> passenger }
    .eachCount()
    .filter { (_, numOfTrips) -> numOfTrips > 1 }
    .keys

/**
 * Subtask 4:
 * Find the passengers who had a discount for the majority of their trips.
 */
internal fun TaxiPark.findSmartPassengers(): Set<Passenger> {
    return trips
        .flatMap { trip ->
            val discountCount = if (trip.discount == null) 0 else 1
            trip.passengers.map { passenger -> passenger to discountCount }
        }.fold(HashMap<Passenger, Int>()) { passengerToDiscountCountMap, (passenger, discountCount) ->
            // We always read current num of discounts and update it with a discount count
            //  If a passenger is not already added to the map, we add it, and then we increment discount count value
            val currentDiscountCount = passengerToDiscountCountMap[passenger] ?: 0
            passengerToDiscountCountMap[passenger] = currentDiscountCount + discountCount

            passengerToDiscountCountMap
        }.filter { it.value > 0 }
        .keys
}
