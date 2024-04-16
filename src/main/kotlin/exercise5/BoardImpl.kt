package exercise5

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard = GameBoardImpl(width)

open class SquareBoardImpl(size: Int) : SquareBoard {
    override val width: Int = size
    private val cells: List<Cell>

    init {
        require(size >= 0) {
            "Invalid size. Please make sure that the size is greater than 0."
        }
        cells = (1..size).flatMap { i -> (1..size).map { j -> Cell(i, j) } }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return cells.find { it.i == i && it.j == j }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j) ?: throw IllegalArgumentException("Invalid cell coordinates.")
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.filter { it in 1..width }.map { getCell(i, it) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.filter { it in 1..width }.map { getCell(it, j) }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            Direction.UP -> getCellOrNull(this.i - 1, this.j)
            Direction.DOWN -> getCellOrNull(this.i + 1, this.j)
            Direction.LEFT -> getCellOrNull(this.i, this.j - 1)
            Direction.RIGHT -> getCellOrNull(this.i, this.j + 1)
        }
    }
}

class GameBoardImpl(size: Int) : SquareBoardImpl(size), GameBoard {
    private val cellValues: MutableMap<Cell, String?> = mutableMapOf()

    override fun get(cell: Cell): String? {
        return cellValues[cell]
    }

    override fun set(cell: Cell, value: String?) {
        cellValues[cell] = value
    }

    override fun filter(predicate: (String?) -> Boolean): Collection<Cell> {
        return cellValues.filterValues(predicate).keys
    }

    override fun find(predicate: (String?) -> Boolean): Cell? {
        return cellValues.entries.find { predicate(it.value) }?.key
    }

    override fun any(predicate: (String?) -> Boolean): Boolean {
        return cellValues.values.any(predicate)
    }

    override fun all(predicate: (String?) -> Boolean): Boolean {
        return cellValues.values.all(predicate)
    }
}