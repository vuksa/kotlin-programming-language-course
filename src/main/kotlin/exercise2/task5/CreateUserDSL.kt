package org.jetbrains.exercise2.task5

internal data class User(
    var firstName: String = "",
    var lastName: String = "",
    var age: Int = -1,
    var address: Address? = null
)

internal data class Address(
    var street: String = "",
    var number: Int = -1,
    var city: String = ""
)

/**
 * Task 5:
 *
 * Implement User DSL by implementing [user] and [address] functions. User DSL should allow the following DSL syntax:
 * ```kotlin
 * user {
 *      firstName = "Petar"
 *      lastName = "Petrovic"
 *      age = 62
 *      address {
 *          street = "Bulevar kralja Petra Prvog"
 *          number = 33
 *          city = "Novi Sad"
 *      }
 *}
 *```
 *
 * The above user dsl function invocation should provide the same output as if user would be instantiated via
 * constructor invocation as in the following code snippet:
 * ```kotlin
 * User(
 *     firstName = "Petar",
 *     lastName = "Petrovic",
 *     age = 62,
 *     address = Address(
 *         street = "Bulevar kralja Petra Prvog",
 *         number = 33,
 *         city = "Novi Sad"
 *     )
 * )
 * ```
 *
 * Make sure to leverage higher-order functions with receivers, and scoped functions.
 */

internal fun user(initUser: User.() -> Unit): User {
    val newUser = User()
    newUser.initUser()
    return newUser
}

internal fun User.address(initAddress: Address.() -> Unit): User {
    val newAddress = Address()
    newAddress.initAddress()
    address = newAddress
    return this
}

fun main() {
    val expectedUser = User(
        firstName = "Petar",
        lastName = "Petrovic",
        age = 62,
        address = Address(
            street = "Bulevar kralja Petra Prvog",
            number = 33,
            city = "Novi Sad"
        )
    )

    val actualUser = user {
        firstName = "Petar"
        lastName = "Petrovic"
        age = 62
        address {
            street = "Bulevar kralja Petra Prvog"
            number = 33
            city = "Novi Sad"
        }
    }

    require(expectedUser == actualUser) {
        "Actual user is not the same as an expected one. Please revisit your dsl code."
    }
}
