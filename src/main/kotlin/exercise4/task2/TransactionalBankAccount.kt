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

enum class TType {
    DEPOSIT, WITHDRAWAL
}

enum class TStatus {
    SUCCESS, FAILURE
}

data class Transaction(
    val transactionDate: LocalDateTime,
    val transactionType: TType,
    val amount: Double,
    val oldBalance: Double,
    val newBalance: Double,
    val transactionStatus: TStatus
)

class TransactionalBankAccount(
    accountNumber: String,
    accHolderName: String,
    balance: Double,
) : BankAccount(accountNumber, accHolderName, balance) {

    private val transactions: MutableList<Transaction> = mutableListOf()

    override fun deposit(amount: Double): Boolean {
        val oldBalance = getBalance()
        super.deposit(amount)
        val newBalance = getBalance()

        transactions.add(Transaction(currentTime, TType.DEPOSIT, amount, oldBalance, newBalance, TStatus.SUCCESS))
        return true
    }

    override fun withdraw(amount: Double): Boolean {
        val oldBalance = getBalance()
        val success = super.withdraw(amount)
        val newBalance = getBalance()
        val status = if (success) TStatus.SUCCESS else TStatus.FAILURE

        transactions.add(Transaction(currentTime, TType.WITHDRAWAL, amount, oldBalance, newBalance, status))
        return success
    }

    fun getAllTransactions(): List<Transaction> {
        return transactions.sortedByDescending { it.transactionDate }
    }

    fun getAllTransactionsBy(predicate: (Transaction) -> Boolean): List<Transaction> {
        return getAllTransactions().filter(predicate)
    }

    fun getTransactionsBetween(startDate: LocalDateTime, endDate: LocalDateTime): List<Transaction> {
        return getAllTransactionsBy { it.transactionDate in startDate..endDate }
    }

    fun getAllFailedTransactions(): List<Transaction> {
        return getAllTransactionsBy { it.transactionStatus == TStatus.FAILURE }
    }

    fun getAllSuccessfulTransaction(): List<Transaction> {
        return getAllTransactionsBy { it.transactionStatus == TStatus.SUCCESS }
    }

    fun getAllFailedDeposits(): List<Transaction> {
        return getAllTransactionsBy {it.transactionType == TType.DEPOSIT && it.transactionStatus == TStatus.FAILURE }
    }

    fun getAllFailedWithdrawals(): List<Transaction> {
        return getAllTransactionsBy {it.transactionType == TType.WITHDRAWAL && it.transactionStatus == TStatus.FAILURE }
    }

    fun getAllSuccessfulDeposits(): List<Transaction> {
        return getAllTransactionsBy { it.transactionType == TType.DEPOSIT && it.transactionStatus == TStatus.SUCCESS }
    }

    fun getAllSuccessfulWithdrawals(): List<Transaction> {
        return getAllTransactionsBy { it.transactionType == TType.WITHDRAWAL && it.transactionStatus == TStatus.SUCCESS}
    }

    override fun displayAccountInfo() {
        super.displayAccountInfo()
        println("Transactions:\n")
        if (transactions.isEmpty()) {
            println("No transactions recorded\n")
        } else {
            transactions.forEach {
                println("Transaction Date: ${it.transactionDate.prettyPrint()}")
                println("Transaction Type: ${it.transactionType}")
                println("Amount: ${it.amount}")
                println("Old Balance: ${it.oldBalance}")
                println("New Balance: ${it.newBalance}")
                println("Status ${it.transactionStatus}")
                println()
            }
        }
    }
}

private val currentTime: LocalDateTime get() = LocalDateTime.now()

private fun LocalDateTime.prettyPrint(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}

fun main() {
    println(currentTime.prettyPrint())
    // Creating a Transactional Bank Account
    val account = TransactionalBankAccount("123456789", "John Doe", 0.0)

    // Displaying account information
    account.displayAccountInfo()

    // Depositing some money
    account.deposit(1000.0)

    // Withdrawing some money
    account.withdraw(500.0)

    // Displaying updated account information
    account.displayAccountInfo()
}
