package exercise5

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.jupiter.api.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestGameBoard {
    operator fun GameBoard.get(i: Int, j: Int) = get(getCell(i, j))
    operator fun GameBoard.set(i: Int, j: Int, value: String?) = set(getCell(i, j), value)

    @Test
    fun test01GetAndSetElement() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        Assert.assertEquals("a", gameBoard[1, 1])
    }

    @Test
    fun test02Filter() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        gameBoard[1, 2] = "b"
        val cells = gameBoard.filter { it == "a" }
        Assert.assertEquals(1, cells.size)
        val cell = cells.first()
        Assert.assertEquals(1, cell.i)
        Assert.assertEquals(1, cell.j)
    }

    @Test
    fun test03All() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        gameBoard[1, 2] = "a"
        Assert.assertFalse(gameBoard.all { it == "a" })
        gameBoard[2, 1] = "a"
        gameBoard[2, 2] = "a"
        Assert.assertTrue(gameBoard.all { it == "a" })
    }

    @Test
    fun test04Any() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        gameBoard[1, 2] = "b"
        Assert.assertTrue(gameBoard.any { it?.toCharArray()?.firstOrNull() in 'a'..'b' })
        Assert.assertTrue(gameBoard.any { it == null })
    }

    @Test
    fun test05TheSameCell() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        val cell1 = gameBoard.find { it == "a" }
        gameBoard[1, 1] = "b"
        val cell2 = gameBoard.find { it == "b" }
        Assert.assertEquals(cell1, cell2)
    }

    @Test
    fun test06FindReturnsTheSameCell() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        val first = gameBoard.find { it == "a" }
        val second = gameBoard.getCell(1, 1)
        Assert.assertTrue(
            "find shouldn't recreate the 'Cell' instances.\n" +
                    "Create only 'width * width' cells; all the functions working with cells\n" +
                    "should return existing cells instead of creating new ones.",
            first === second
        )
    }

    @Test
    fun test07FilterTheSameCell() {
        val gameBoard = createGameBoard<Char>(2)
        gameBoard[1, 1] = "a"
        val cells = gameBoard.filter { it == "a" }
        val first = cells.first()
        val second = gameBoard.getCell(1, 1)
        Assert.assertTrue(
            "'filter' shouldn't recreate the 'Cell' instances.\n" +
                    "Create only 'width * width' cells; all the functions working with cells\n" +
                    "should return existing cells instead of creating new ones.",
            first === second
        )
    }
}