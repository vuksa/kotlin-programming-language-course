package exercise5

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard = GameBoardImpl(width)

open class SquareBoardImpl(size: Int) : SquareBoard{
    override val width: Int = size

    protected val board = List(size) { x ->
        List(size) { y ->
            Cell(x + 1, y + 1)
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return board.getOrNull(i - 1)?.getOrNull(j - 1)
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException("Wrong parameter")
    }

    override fun getAllCells(): Collection<Cell> {
        return board.flatten()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.mapNotNull { getCellOrNull(i, it) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.mapNotNull { getCellOrNull(it, j) }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction){
            Direction.UP -> getCellOrNull(i - 1, j)
            Direction.DOWN -> getCellOrNull(i + 1, j)
            Direction.LEFT -> getCellOrNull(i, j - 1)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
        }
    }
}

class GameBoardImpl(size: Int) : SquareBoardImpl(size), GameBoard{

    private val cells = mutableMapOf<Cell, String?>()

    init {
        board.flatten().forEach {
            cells[it] = null
        }
    }

    override fun get(cell: Cell): String? {
        return cells[cell]
    }

    override fun set(cell: Cell, value: String?) {
        cells[cell] = value
    }

    override fun filter(predicate: (String?) -> Boolean): Collection<Cell> {
        return  cells.filter { predicate(it.value) }.keys
    }

    override fun find(predicate: (String?) -> Boolean): Cell? {
        return cells.entries.find { predicate(it.value) }?.key
    }

    override fun any(predicate: (String?) -> Boolean): Boolean {
        return cells.entries.any{ predicate(it.value)}
    }

    override fun all(predicate: (String?) -> Boolean): Boolean {
        return cells.entries.all { predicate(it.value) }
    }

}