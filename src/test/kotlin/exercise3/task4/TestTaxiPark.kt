package exercise3.task4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class TestTaxiPark {

    @Test
    fun `test- find fake drivers`() {
        val tp = taxiPark(1..3, 1..2, trip(1, 1), trip(1, 2))
        assertEquals(
            drivers(2, 3).toSet(),
            tp.findFakeDrivers(),
            "Wrong result for 'findFakeDrivers()'." + tp.display()
        )
    }

    @Test
    fun `test - find faithful passengers`() {
        val tp =
            taxiPark(
                1..3,
                1..5,
                trip(1, 1),
                trip(2, 1),
                trip(1, 4),
                trip(3, 4),
                trip(1, 5),
                trip(2, 5),
                trip(2, 2)
            )
        assertEquals(
            passengers(1, 4, 5),
            tp.findFaithfulPassengers(2),
            "Wrong result for 'findFaithfulPassengers()'. MinTrips: 2." + tp.display()
        )
    }

    @Test
    fun `test - find faithful passengers1`() {
        val tp =
            taxiPark(
                1..3,
                1..5,
                trip(1, 1),
                trip(2, 1),
                trip(1, 4),
                trip(3, 4),
                trip(1, 5),
                trip(2, 5),
                trip(2, 2)
            )
        assertEquals(
            passengers(1, 2, 4, 5),
            tp.findFaithfulPassengers(0),
            "Wrong result for 'findFaithfulPassengers()'. MinTrips: 3." + tp.display()
        )
    }

    @Test
    fun `test frequent passengers`() {
        val tp = taxiPark(
            1..2,
            1..4,
            trip(1, 1),
            trip(1, 1),
            trip(1, listOf(1, 3)),
            trip(1, 3),
            trip(1, 2),
            trip(2, 2)
        )
        assertEquals(
            passengers(1, 3),
            tp.findFrequentPassengers(driver(1)),
            "Wrong result for 'findFrequentPassengers()'. Driver: ${driver(1).name}." + tp.display()
        )
    }

    @Test
    fun `test - find smart passengers`() {
        val tp = taxiPark(
            1..2,
            1..2,
            trip(1, 1, discount = 0.1),
            trip(2, 2)
        )
        assertEquals(
            passengers(1),
            tp.findSmartPassengers(),
            "Wrong result for 'findSmartPassengers()'." + tp.display()
        )
    }
}
