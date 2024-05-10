package exercise6.taskProjections.task1

/**
 * Task: Projections #1
 * In this task, you need to work with projections.
 * First of all, you need to make it possible to create different types of MailBox.
 * Then, you need to modify code somehow to be able to create topRatedPostman and juniorPostman:
 *  - The topRatedPostman can send ONLY express postcards
 *  - The juniorPostman can send both regular and express postcards


**/

private interface Sender<in T> {
    fun send(item: T)
}

private class MailBox<T>(private var box: T? = null): Sender<T> {
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

private class Postman<T>(private val mailboxes: List<Sender<T>>): Sender<T> {
    override fun send(item: T) {
        mailboxes.forEach { it.send(item) }
    }

}
private interface Delivery

private open class Postcard(open val origin: String) : Delivery

private data class ExpressPostcard(val priceEuro: Int, override val origin: String) : Postcard(origin)

fun main() {
    val postcardStorage = MailBox<Postcard>()
    val expressPostcardStorage = MailBox<ExpressPostcard>()

    val expressPostcard = ExpressPostcard(15, "Serbia")
    val postcard = Postcard("Germany")

    // TODO: add code to create topRatedPostman and juniorPostman.
    //  The topRatedPostman can send ONLY express postcards
    //  The juniorPostman can send both regular and express postcards
    val topRatedPostman = Postman(listOf(postcardStorage, expressPostcardStorage as Sender<ExpressPostcard>))
    topRatedPostman.send(expressPostcard)
//    topRatedPostman.send(postcard) // ERROR

    val juniorPostman = Postman(listOf(postcardStorage))
    juniorPostman.send(postcard)
    juniorPostman.send(expressPostcard)
}