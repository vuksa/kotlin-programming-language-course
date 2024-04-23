package exercise5

data class Cell(val i: Int, val j: Int) {
    override fun toString()= "($i, $j)"
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT;

    fun reversed() = when (this) {
        UP -> DOWN
        DOWN -> UP
        RIGHT -> LEFT
        LEFT -> RIGHT
    }
}

interface SquareBoard {
    val width: Int

    fun getCellOrNull(i: Int, j: Int): Cell?
    fun getCell(i: Int, j: Int): Cell

    fun getAllCells(): Collection<Cell>

    fun getRow(i: Int, jRange: IntProgression): List<Cell>
    fun getColumn(iRange: IntProgression, j: Int): List<Cell>

    fun Cell.getNeighbour(direction: Direction): Cell?
}

interface GameBoard : SquareBoard {

    operator fun get(cell: Cell): String?
    operator fun set(cell: Cell, value: String?)

    fun filter(predicate: (String?) -> Boolean): Collection<Cell>
    fun find(predicate: (String?) -> Boolean): Cell?
    fun any(predicate: (String?) -> Boolean): Boolean
    fun all(predicate: (String?) -> Boolean): Boolean
}
