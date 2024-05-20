package exercise6.taskProjections.task1

/**
 * Task: Projections #1
 * In this task, you need to work with projections.
 * First of all, you need to make it possible to create different types of MailBox.
 * Then, you need to modify code somehow to be able to create topRatedPostman and juniorPostman:
 *  - The topRatedPostman can send ONLY express postcards
 *  - The juniorPostman can send both regular and express postcards


**/

interface Sender<T>{
    fun send(item: T)
}

class MailBox <T> (private var box: T? = null): Sender<T> {
    override fun send(item: T) {
        printCurrentBoxState()
        println("Sending the box: $item!")
        box = item
    }

    private fun printCurrentBoxState() {
        if (box != null) {
            println("I have a box: $box!")
        } else  {
            println("I have nothing")
        }
    }

}

class Postman<T>(private val mailboxes: List<Sender<T>>): Sender<T> {
    override fun send(item: T) {
        mailboxes.forEach { it.send(item) }
    }

}
interface Delivery

open class Postcard(open val origin: String) : Delivery

data class ExpressPostcard(val priceEuro: Int, override val origin: String) : Postcard(origin)

fun main() {
    val postcardStorage = MailBox<Postcard>()
    val expressPostcardStorage = MailBox<ExpressPostcard>()

    val expressPostcard = ExpressPostcard(15, "Serbia")
    val postcard = Postcard("Germany")

    val topRatedPostman: Postman<ExpressPostcard> = Postman(listOf(expressPostcardStorage))
    val juniorPostman: Postman<Postcard> = Postman(listOf(postcardStorage))

    topRatedPostman.send(expressPostcard)
    juniorPostman.send(expressPostcard)
    juniorPostman.send(postcard)
}