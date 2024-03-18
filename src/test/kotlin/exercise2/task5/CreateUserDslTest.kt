package exercise2.task5

import org.jetbrains.exercise2.task5.Address
import org.jetbrains.exercise2.task5.User
import org.jetbrains.exercise2.task5.address
import org.jetbrains.exercise2.task5.user
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateUserDslTest {

    @Test
    fun `test - User DSL creates a valid user`() {
        users.forEach { expectedUser ->
            val actualUser = user {
                firstName = expectedUser.firstName
                lastName = expectedUser.lastName
                age = expectedUser.age
                address {
                    street = expectedUser.address!!.street
                    number = expectedUser.address!!.number
                    city = expectedUser.address!!.city
                }
            }

            assertEquals(
                expectedUser,
                actualUser,
                "User $actualUser does not match expected expectedUser: $expectedUser"
            )
        }
    }

    companion object {
        private val users = listOf(
            User(
                firstName = "Petar",
                lastName = "Petrovic",
                age = 62,
                address = Address(street = "Bulevar kralja Petra Prvog", number = 33, city = "Novi Sad")
            ),
            User(
                firstName = "John", lastName = "Doe", age = 30, address = Address(
                    street = "Main Street",
                    number = 10,
                    city = "New York"
                )
            ),
            User(
                firstName = "Alice", lastName = "Smith", age = 45, address = Address(
                    street = "Oak Avenue",
                    number = 25,
                    city = "Los Angeles"
                )
            ),
            User(
                firstName = "Bob", lastName = "Johnson", age = 55, address = Address(
                    street = "Elm Street",
                    number = 42,
                    city = "Chicago"
                )
            ),
            User(
                firstName = "Emma",
                lastName = "Brown",
                age = 28,
                address = Address(street = "Cedar Street", number = 17, city = "San Francisco")
            ),
            User(
                firstName = "David", lastName = "Wilson", age = 40, address = Address(
                    "Pine Road",
                    5,
                    "Seattle"
                )
            ),
            User(
                firstName = "Olivia", lastName = "Lee", age = 50, address = Address(
                    street = "Maple Avenue",
                    number = 8,
                    city = "Boston"
                )
            ),
            User(
                firstName = "Sophia", lastName = "Wong", age = 35, address = Address(
                    street = "Willow Lane",
                    number = 21,
                    city = "Houston"
                )
            ),
            User(
                firstName = "Michael",
                lastName = "Garcia",
                age = 48,
                address = Address("Birch Boulevard", 12, "Miami")
            ),
            User(firstName = "Emily", lastName = "Martinez", age = 27, address = Address("Cherry Lane", 30, "Dallas"))
        )
    }
}
