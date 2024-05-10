package exercise4.task2

import exercise4.task1.BankAccount
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Transactional Bank Account Assignment
 *
 * Objective:
 * Implement a Transactional Bank Account system using Kotlin and Object-Oriented Programming principles.
 * Transactional Bank Account should extend [exercise4.task1.BankAccount] from exercise4.task1 and add capability to record
 * all transactions made with the account.
 *
 * 1. Create [TransactionalBankAccount] class which **extends** [exercise4.task1.BankAccount] class,
 * and adds capability to record transaction on every deposit or withdrawal.
 *
 * 2. Transaction has the following attributes:
 *      - [transactionDate]: a date [LocalDateTime] when the transaction was made.
 *      - [transactionType]: a type of transaction can be [DEPOSIT] or [WITHDRAWAL]. Modeled as an enum.
 *      - [amount]: the amount that was deposited / withdrawn from the balance of the account.
 *      - [oldBalance]: the balance of the account before the transaction.
 *      - [newBalance]: the balance of the account after the transaction.
 *      - [transactionStatus] - status of transaction. [SUCCESS] if it was successful, [FAILURE] otherwise. Modeled as an enum.
 *
 * 3. Implement the following methods in [TransactionalBankAccount] class:
 *      - [deposit]: adds the specified amount to the account balance
 *        and records [DEPOSIT] transaction with a current time, amount and status.
 *      - [withdraw]: deducts the specified amount from the account balance if sufficient funds are available,
 *        returns [true] of transaction was successful [false] otherwise.
 *        In addition to this, it records [WITHDRAWAL] transaction with a current time,
 *        amount and [SUCCESS] status if the transaction was successful. Otherwise, transaction should have [FAILURE] status.
 *      - [getBalance]: returns the current balance of the account.
 *      - [getAllTranactions]: returns the list of all transactions sorted descending by transaction time.
 *      - [getAllTransactionsBy(predicate: (Transaction) -> Boolean)]: returns the list of all transactions that satisfy the provided predicate,
 *        sorted descending by transaction time.
 *      - [getTransactionsBetween(startDate: LocalDateTime, endDate: LocalDateTime)]: returns the list of transactions
 *        that occurred between the specified start and end dates, sorted descending by transaction time.
 *      - [getAllFailedTransactions]: returns the list of all transactions with a [FAILURE] status, sorted descending by transaction time.
 *      - [getAllSuccessfulTransactions]: returns the list of all transactions with a [SUCCESS] status, sorted descending by transaction time.
 *      - [getAllFailedDeposits]: returns the list of all failed deposit transactions, sorted descending by transaction time.
 *      - [getAllFailedWithdrawals]: returns the list of all failed withdrawal transactions, sorted descending by transaction time.
 *      - [getAllSuccessfulDeposits]: returns the list of all successful deposit transactions, sorted descending by transaction time.
 *      - [getAllSuccessfulWithdrawals]: returns the list of all successful withdrawal transactions, sorted descending by transaction time.
 *
 * 4. [TransactionalBankAccount] class should override the [displayAccountInfo] method to include transaction details.
 *      - Information should be printed in the following format:
 *      ```
 *      Account Holder: [ACCOUNT_HOLDER_NAME]
 *      Account Number: [ACCOUNT_NUMBER]
 *      Balance: [ACCOUNT_BALANCE]
 *
 *      Transactions:
 *
 *      Transaction Date: [PRETTY_FORMATED_TRANSACTION_DATE]
 *      Transaction Type: [TRANSACTION_TYPE]
 *      Amount: [TRANSACTION_AMOUNT]
 *      Old Balance: [OLD_BALANCE]
 *      New Balance: [NEW_BALANCE]
 *      Status: [TRANSACTION_STATUS]
 *
 *      Transaction Date: [PRETTY_FORMATED_TRANSACTION_DATE]
 *      Transaction Type: [TRANSACTION_TYPE]
 *      Amount: [TRANSACTION_AMOUNT]
 *      Old Balance: [OLD_BALANCE]
 *      New Balance: [NEW_BALANCE]
 *      Status: [TRANSACTION_STATUS]
 *      ```
 *     If there is no transaction information should be printed as following:
 *     ```
 *      Account Holder: [ACCOUNT_HOLDER_NAME]
 *      Account Number: [ACCOUNT_NUMBER]
 *      Balance: [ACCOUNT_BALANCE]
 *
 *      Transactions:
 *
 *      No transactions recorded.
 *      ```
 */

data class Transaction(
    val dateTime: LocalDateTime,
    val amount: Int,
    val oldBalance: Int,
    val newBalance: Int,
    val type: Type,
    val status: STATUS
) {
    enum class Type {
        DEPOSIT, WITHDRAWAL
    }

    enum class STATUS {
        SUCCESS, FAILURE
    }
}

class TransactionalBankAccount(
    accountHolderName: String,
    accountNumber: String
) : BankAccount(accountHolderName, accountNumber) {
    private val transactions = mutableListOf<Transaction>()

    override fun withdraw(amount: Int): Boolean = doTransaction(amount, Transaction.Type.WITHDRAWAL)

    override fun deposit(amount: Int): Boolean = doTransaction(amount, Transaction.Type.DEPOSIT)

    override fun displayAccountInfo() {
        super.displayAccountInfo()

        val accountInfo = buildString {
            appendLine()
            appendLine("Transactions:")
            appendLine()

            if (transactions.isEmpty()) {
                println("No transactions recorded.")
            } else {
                val transactionTemplate = """
                    Transaction Date: [PRETTY_FORMATED_TRANSACTION_DATE]
                    Transaction Type: [TRANSACTION_TYPE]
                    Amount: [TRANSACTION_AMOUNT]
                    Old Balance: [OLD_BALANCE]
                    New Balance: [NEW_BALANCE]
                    Status: [TRANSACTION_STATUS]
                """.trimIndent()

                transactions.forEach { transaction ->
                    transactionTemplate
                        .replace("[PRETTY_FORMATED_TRANSACTION_DATE]", transaction.dateTime.prettyPrint())
                        .replace("[TRANSACTION_TYPE]", transaction.type.toString())
                        .replace("[TRANSACTION_AMOUNT]", transaction.amount.toString())
                        .replace("[OLD_BALANCE]", transaction.oldBalance.toString())
                        .replace("[NEW_BALANCE]", transaction.newBalance.toString())
                        .replace("[TRANSACTION_STATUS]", transaction.status.toString())
                        .let(::appendLine)

                    appendLine()
                }
            }
        }

        println(accountInfo)
    }

    private fun doTransaction(amount: Int, type: Transaction.Type): Boolean {
        val currentTime = currentTime
        val oldBalance = balance
        val status = doTransactionWithResult(type, amount)
        val newBalance = balance

        transactions += Transaction(
            currentTime,
            amount,
            oldBalance,
            newBalance,
            type,
            status
        )

        return status == Transaction.STATUS.SUCCESS
    }

    private fun doTransactionWithResult(
        type: Transaction.Type,
        amount: Int
    ): Transaction.STATUS {
        val isSuccess = when (type) {
            Transaction.Type.DEPOSIT -> super.deposit(amount)
            Transaction.Type.WITHDRAWAL -> super.withdraw(amount)
        }

        return when {
            isSuccess -> Transaction.STATUS.SUCCESS
            else -> Transaction.STATUS.FAILURE
        }
    }
}


private val currentTime: LocalDateTime get() = LocalDateTime.now()

private fun LocalDateTime.prettyPrint(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}

fun main() {
    // Creating a Transactional Bank Account
    val account = TransactionalBankAccount("123456789", "John Doe")

    // Depositing some money
    account.deposit(1000)

    // Withdrawing some money
    account.withdraw(500)

    account.withdraw(600)

    // Displaying updated account information
    account.displayAccountInfo()
}
