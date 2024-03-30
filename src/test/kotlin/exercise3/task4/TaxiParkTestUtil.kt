package exercise3.task4

internal fun driver(i: Int) = Driver("D-$i")
internal fun passenger(i: Int) = Passenger("P-$i")

internal fun drivers(indices: List<Int>) = indices.map(::driver).toSet()
internal fun drivers(range: IntRange) = drivers(range.toList())
internal fun drivers(vararg indices: Int) = drivers(indices.toList())

internal fun passengers(indices: List<Int>) = indices.map(::passenger).toSet()
internal fun passengers(range: IntRange) = passengers(range.toList())
internal fun passengers(vararg indices: Int) = passengers(indices.toList())

internal fun taxiPark(driverIndexes: IntRange, passengerIndexes: IntRange, vararg trips: Trip) =
        TaxiPark(drivers(driverIndexes), passengers(passengerIndexes), trips.toList())

internal fun trip(driverIndex: Int, passengerIndexes: List<Int>, duration: Int = 10, distance: Double = 3.0, discount: Double? = null) =
        Trip(driver(driverIndex), passengers(passengerIndexes), duration, distance, discount)

internal fun trip(driverIndex: Int, passenger: Int, duration: Int = 10, distance: Double = 3.0, discount: Double? = null) =
        Trip(driver(driverIndex), passengers(passenger), duration, distance, discount)

internal fun TaxiPark.display() = buildString {
    appendLine()
    appendLine("Taxi park:")
    appendLine("Drivers: ${allDrivers.map { it.name }}")
    appendLine("Passengers: ${allPassengers.map { it.name }}")
    appendLine("Trips: ${trips.map { it.display() }}")
}

internal fun Trip.display(): String {
    val discountText = discount?.let { ", $it" } ?: ""
    return "(${driver.name}, ${passengers.map { it.name }}, $duration, $distance$discountText)"
}
