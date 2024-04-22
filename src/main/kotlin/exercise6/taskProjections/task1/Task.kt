package exercise6.taskProjections.task1

/**
 * Task: Projections #1
 * In this task, you need to work with projections.
 * First of all, you need to make it possible to create different types of MailBox.
 * Then, you need to modify code somehow to be able to create topRatedPostman and juniorPostman:
 *  - The topRatedPostman can send ONLY express postcards
 *  - The juniorPostman can send both regular and express postcards


**/

interface Sender {
    fun send(item: Any)
}

class MailBox(private var box: Any? = null): Sender {
    override fun send(item: Any) {
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

class Postman(private val mailboxes: List<Sender>): Sender {
    override fun send(item: Any) {
        mailboxes.forEach { it.send(item) }
    }

}
interface Delivery

open class Postcard(open val origin: String) : Delivery

data class ExpressPostcard(val priceEuro: Int, override val origin: String) : Postcard(origin)

fun main() {
    // TODO: This code should became compilable
//    val postcardStorage = MailBox<Postcard>()
//    val expressPostcardStorage = MailBox<ExpressPostcard>()

    val expressPostcard = ExpressPostcard(15, "Serbia")
    val postcard = Postcard("Germany")

    // TODO: add code to create topRatedPostman and juniorPostman.
    //  The topRatedPostman can send ONLY express postcards
    //  The juniorPostman can send both regular and express postcards
}