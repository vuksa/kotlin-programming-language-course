package exercise4.task1

/**
 * Bank Account Assignment
 *
 * Objective:
 * Implement a simple Bank Account system using Kotlin and Object-Oriented Programming principles.
 *
 * Requirements:
 * 1. Create a [BankAccount] class with the following attributes:
 *    - [accountNumber]: a unique identifier for each bank account.
 *    - [accountHolderName]: name of the account holder.
 *    - [balance]: current balance in the account.
 *
 * 2. Implement the following methods in the [BankAccount] class:
 *    - [deposit]: adds the specified amount to the account balance.
 *    - [withdraw]: deducts the specified amount from the account balance if sufficient funds are available,
 *      returns `true` if the transaction was successful, `false` otherwise.
 *    - [getBalance]: returns the current balance of the account.
 *    - [displayAccountInfo]: prints out the account information including account number, account holder name, and current balance.
 *      - Information should be printed in the following format:
 *      ```
 *      Account Holder: [ACCOUNT_HOLDER_NAME]
 *      Account Number: [ACCOUNT_NUMBER]
 *      Balance: [ACCOUNT_BALANCE]
 *      ```
 * 3. Ensure that withdrawal is only allowed if the withdrawal amount is less than or equal to the current balance.
 *
 * 4. Create two class constructors:
 *    - One constructor accepts the account holder name, account holder number, and initial balance.
 *    - The second constructor accepts only the account holder name and account holder number.
 *      The initial balance for this constructor should always be set to 0.
 */


open class BankAccount(
    private val accountHolderName: String,
    private val accountNumber: String,
    protected var balance: Int
) {

    constructor(accountHolderName: String, accountNumber: String) : this(accountHolderName, accountNumber, 0)

    open fun deposit(amount: Int): Boolean {
        balance += amount
        return true
    }

    open fun withdraw(amount: Int): Boolean {
        if (amount > balance) return false

        balance -= amount

        return true
    }

    open fun displayAccountInfo() {
        val template = """
            Account Holder: [ACCOUNT_HOLDER_NAME]
            Account Number: [ACCOUNT_NUMBER]
            Balance: [ACCOUNT_BALANCE]
        """.trimIndent()

        template
            .replace("[ACCOUNT_HOLDER_NAME]", accountHolderName)
            .replace("[ACCOUNT_NUMBER]", accountNumber)
            .replace("[ACCOUNT_BALANCE]", balance.toString())
            .let { println(it) }
    }
}


fun main() {
    // Creating a Bank Account
    val account = BankAccount("123456789", "John Doe")

    // Displaying account information
    account.displayAccountInfo()

    // Depositing some money
    account.deposit(1000)

    // Withdrawing some money
    account.withdraw(500)

    // Displaying updated account information
    account.displayAccountInfo()
}
