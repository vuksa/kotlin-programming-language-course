package exercise6.taskProjections.task1

/**
 * Task: Projections #1
 * In this task, you need to work with projections.
 * First of all, you need to make it possible to create different types of MailBox.
 * Then, you need to modify code somehow to be able to create topRatedPostman and juniorPostman:
 *  - The topRatedPostman can send ONLY express postcards
 *  - The juniorPostman can send both regular and express postcards
**/

interface Sender<T: Any?> {
    fun send(item: T)
}

class MailBox<T: Postcard>(private var box: T? = null): Sender<T> {
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

open class Postman<T : Postcard>(private val mailboxes: List<Sender<out T>>): Sender<T> {
    override fun send(item: T) {
        mailboxes.forEach { it.send(item) }
    }
}

class JuniorPostman(private val mailboxes: List<Sender<out Postcard>>): Postman<Postcard>(mailboxes) {
    override fun send(item: Postcard) {
        super.send(item)
    }
}

class TopRatedPostman(private val mailboxes: List<Sender<ExpressPostcard>>): Postman<ExpressPostcard>(mailboxes) {
    override fun send(item: ExpressPostcard) {
        super.send(item)
    }
}

interface Delivery

open class Postcard(open val origin: String) : Delivery

data class ExpressPostcard(val priceEuro: Int, override val origin: String) : Postcard(origin)

fun main() {
    // TODO: This code should became compilable
    val postcardStorage = MailBox<Postcard>()
    val expressPostcardStorage = MailBox<ExpressPostcard>()

    val expressPostcard = ExpressPostcard(15, "Serbia")
    val postcard = Postcard("Germany")

    val sendersForJuniorPostman: List<MailBox<out Postcard>> = List(10) {
        if (it % 2 == 0) MailBox()
        else MailBox<ExpressPostcard>()
    }

    val sendersForTopRatedPostman = List(20) {
        MailBox<ExpressPostcard>()
    }

    // TODO: add code to create topRatedPostman and juniorPostman.
    //  The topRatedPostman can send ONLY express postcards
    //  The juniorPostman can send both regular and express postcards
    val juniorPostman = JuniorPostman(sendersForJuniorPostman)
    val topRatedPostman = TopRatedPostman(sendersForTopRatedPostman)
}