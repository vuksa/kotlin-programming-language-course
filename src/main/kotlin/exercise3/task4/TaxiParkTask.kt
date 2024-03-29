package exercise3.task4

import kotlin.math.min

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
    return this.allDrivers.filter { driver -> trips.none { it.driver == driver } }.toSet()
}

/**
 * Subtask 2:
 * Find all the clients who completed at least the given number of trips.
 */
internal fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {

    return this.allPassengers.filter { passenger ->
        trips.count { it.passengers.contains(passenger) } >= minTrips }.toSet()
}

/**
 * Subtask 3:
 * Find all the passengers, who were taken by a given driver more than once.
 */
internal fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {
    return this.allPassengers.filter { passenger ->
        trips.count { (it.driver == driver) and (it.passengers.contains(passenger)) } > 1  }.toSet()
}

/**
 * Subtask 4:
 * Find the passengers who had a discount for the majority of their trips.
 */
internal fun TaxiPark.findSmartPassengers(): Set<Passenger> {

    /*
    Kako bih mogao da recikliram kod "it.passengers.contains(passenger)",
    da ne brojim dva puta?
    */
    return this.allPassengers
        .filter { passenger ->
             trips.count {  ( it.passengers.contains(passenger) ) and ( it.discount != null) } >
                    trips.count{ it.passengers.contains(passenger) } / 2 }.toSet()
}
