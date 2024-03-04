package exercise1.task1

import common.TestCase
import org.jetbrains.exercise1.task1.calculateGrade
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GradeCalculatorTest : TestCase() {

    @Test
    fun `test - calculate Grade with valid scores`() {
        assertEquals(10, calculateGrade(100))
        assertEquals(10, calculateGrade(91))
        assertEquals(9, calculateGrade(90))
        assertEquals(9, calculateGrade(81))
        assertEquals(8, calculateGrade(80))
        assertEquals(8, calculateGrade(71))
        assertEquals(7, calculateGrade(70))
        assertEquals(7, calculateGrade(61))
        assertEquals(6, calculateGrade(60))
        assertEquals(6, calculateGrade(51))
    }

    @Test
    fun `test calculate Grade with invalid scores`() {
        assertThrows<IllegalArgumentException> { calculateGrade(101) }
        assertThrows<IllegalArgumentException> { calculateGrade(-1) }
    }
}