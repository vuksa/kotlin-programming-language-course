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

private val currentTime: LocalDateTime get() = LocalDateTime.now()

private fun LocalDateTime.prettyPrint(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
    return this.format(formatter)
}

fun main() {
    // Creating a Transactional Bank Account
    val account = TransactionalBankAccount("123456789", "John Doe")

    // Making some transactions (successful and unsuccessful)
    account.deposit(1000.0)
    account.withdraw(500.0)
    account.deposit(-100.0)
    account.withdraw(2000.0)
    account.deposit(500.0)
    account.withdraw(100.0)

    // Displaying updated account information
    account.displayAccountInfo()

    println("All Transactions Dates:")
    account.getAllTransactions().forEach { println(it.transactionDate.prettyPrint()) }

    println("\nTransactions with Amount > 600:")
    account.getAllTransactionsBy { it.amount > 600 }.forEach{ println(it.amount) }

    println("\nTransactions in the Last Hour:")
    account.getTransactionsBetween(LocalDateTime.now().minusHours(1), LocalDateTime.now()).forEach { println(it.amount) }

    println("\nAll Successful Transactions:")
    account.getAllSuccessfulTransactions().forEach { println(it.amount) }

    println("\nAll Failed Transactions:")
    account.getAllFailedTransactions().forEach { println(it.amount) }

    println("\nAll Successful Deposits:")
    account.getAllSuccessfulDeposits().forEach { println(it.amount) }

    println("\nAll Successful Withdrawals:")
    account.getAllSuccessfulWithdrawals().forEach { println(it.amount) }

    println("\nAll Failed Deposits:")
    account.getAllFailedDeposits().forEach { println(it.amount) }

    println("\nAll Failed Withdrawals:")
    account.getAllFailedWithdrawals().forEach { println(it.amount) }
}

class TransactionalBankAccount( accountNumber: String, accountHolderName: String, balance: Double = 0.0):
    BankAccount(accountNumber, accountHolderName, balance) {

    private val transactions = mutableListOf<Transaction>()

    fun getAllTransactions(): List<Transaction> = transactions.sortedByDescending { it.transactionDate }

    fun getAllTransactionsBy(predicate: (Transaction) -> Boolean) = transactions.filter(predicate)
                                                                                .sortedByDescending{it.transactionDate}
    fun getTransactionsBetween(startDate: LocalDateTime, endDate: LocalDateTime) = getAllTransactionsBy { it.transactionDate in startDate.rangeUntil(endDate) }

    fun getAllFailedTransactions() = getAllTransactionsBy { it.transactionStatus == TransactionStatus.FAILURE }
    fun getAllSuccessfulTransactions() = getAllTransactionsBy { it.transactionStatus == TransactionStatus.SUCCESS }

    fun getAllFailedDeposits() = getAllFailedTransactions().filter { it.transactionType == TransactionType.DEPOSIT }
    fun getAllFailedWithdrawals() = getAllFailedTransactions().filter { it.transactionType == TransactionType.WITHDRAW }
    fun getAllSuccessfulDeposits() = getAllSuccessfulTransactions().filter { it.transactionType == TransactionType.DEPOSIT }
    fun getAllSuccessfulWithdrawals() = getAllSuccessfulTransactions().filter { it.transactionType == TransactionType.WITHDRAW }

    override fun deposit(amount: Double): Boolean {
        val status = if (super.deposit(amount)) TransactionStatus.SUCCESS else TransactionStatus.FAILURE
        transactions.add(
            Transaction(
                TransactionType.DEPOSIT,
                amount,
                balance - amount,
                balance,
                status
            )
        )
        return status == TransactionStatus.SUCCESS
    }

    override fun withdraw(amount: Double): Boolean {
        val transactionStatus: TransactionStatus = if (super.withdraw(amount)) TransactionStatus.SUCCESS
        else TransactionStatus.FAILURE
        val newTransaction = Transaction(TransactionType.WITHDRAW,
                            amount,
                            balance + amount,
                            balance,
                            transactionStatus)
        transactions.add(newTransaction)
        return transactionStatus == TransactionStatus.SUCCESS
    }

    override fun displayAccountInfo() {
        println(
            """
                Account Holder: $accountHolderName
                Account Number: $accountNumber
                Balance: $balance
                
                Transactions:
            """.trimIndent())
        if (transactions.isEmpty()) println("No transactions recorded.\n".indent(4))
        else transactions.forEach {
                println(
                    """        
                      Transaction Date: ${it.transactionDate.prettyPrint()}
                      Transaction Type: ${it.transactionType}
                      Amount: ${it.amount}
                      Old Balance: ${it.oldBalance}
                      New Balance: ${it.newBalance}
                      Status: ${it.transactionStatus}\n
                    """.trimIndent().indent(4))
            }
    }
}

data class Transaction (val transactionType: TransactionType, val amount: Double, val oldBalance: Double,
                        val newBalance: Double, val transactionStatus: TransactionStatus, val transactionDate: LocalDateTime = currentTime,)

enum class TransactionType {
    WITHDRAW, DEPOSIT
}

enum class TransactionStatus {
    SUCCESS, FAILURE
}
