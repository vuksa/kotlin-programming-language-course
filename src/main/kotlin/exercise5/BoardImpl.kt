package exercise5

import javax.sql.rowset.serial.SQLOutputImpl

fun createSquareBoard(width: Int): SquareBoard {
    return SquareBoardImpl(width)
}

fun <T> createGameBoard(width: Int): GameBoard = TODO("GameBoardImpl(width)")

open class SquareBoardImpl(size: Int) : SquareBoard {
    val board: List<List<Cell>> = List(size) { x ->
        List(size) { y ->
            Cell(x + 1, y + 1)
        }
    }
    override val width: Int
        get() = this.width

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return board.getOrNull(i - 1)?.getOrNull(j - 1)
    }

    override fun getCell(i: Int, j: Int): Cell {
        return board [i - 1][j - 1]
    }

    override fun getAllCells(): Collection<Cell> {
        return board.flatten()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.map { item ->
            board[i - 1][item - 1]
        }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.map { item ->
            board[item - 1][j - 1]
        }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
       return when(direction) {
            Direction.UP -> -1 to 0
            Direction.DOWN -> 1 to 0
            Direction.RIGHT -> 0 to 1
            Direction.LEFT -> 0 to -1
        }.let { (xOffset, yOffset) ->
            getCellOrNull(i + xOffset, j + yOffset)
        }
    }

}


class GameBoardImpl(size: Int) : SquareBoardImpl(size), GameBoard {
    private val valMap = mutableMapOf<Cell, String?>()

    override fun get(cell: Cell): String? {
        return valMap.get(cell)
    }

    override fun set(cell: Cell, value: String?) {
        valMap[cell] = value
    }

    override fun filter(predicate: (String?) -> Boolean): Collection<Cell> {
        return valMap.filter { predicate(it.value) }.keys
    }

    override fun find(predicate: (String?) -> Boolean): Cell? {
        return valMap.entries.find { predicate(it.value) }?.key
    }

    override fun any(predicate: (String?) -> Boolean): Boolean {
        return valMap.entries.any { predicate(it.value) }
    }

    override fun all(predicate: (String?) -> Boolean): Boolean {
        return valMap.entries.all { predicate(it.value) }
    }

}

fun main() {
    var Board = createSquareBoard(2)
    println(Board.getAllCells())
}