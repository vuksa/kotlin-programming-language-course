package exercise5

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

open class SquareBoardImpl(size: Int) : SquareBoard {
    private val board = List(size) { x ->
        List(size) { y ->
            Cell(x + 1, y + 1)
        }
    }

    override val width: Int
        get() = board.count()

    override fun getCellOrNull(i: Int, j: Int): Cell? = board
        .getOrNull(i - 1)
        ?.getOrNull(j - 1)

    override fun getCell(i: Int, j: Int): Cell = getCellOrNull(i, j)
        ?: error("Expected not-null element for index ($i, $j)")

    override fun getAllCells(): Collection<Cell> = board.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> = jRange.mapNotNull { j -> getCellOrNull(i, j) }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> = iRange.mapNotNull { i -> getCellOrNull(i, j) }

    override fun Cell.getNeighbour(direction: Direction): Cell? = when (direction) {
        Direction.UP -> getCellOrNull(this.i - 1, this.j)
        Direction.DOWN -> getCellOrNull(this.i + 1, this.j)
        Direction.RIGHT -> getCellOrNull(this.i, this.j + 1)
        Direction.LEFT -> getCellOrNull(this.i, this.j - 1)
    }
}

class GameBoardImpl<T>(size: Int) : SquareBoardImpl(size), GameBoard<T> {
    private val catalog: MutableMap<Cell, T?> = getAllCells()
        .associateWith { null }
        .toMutableMap()

    override fun get(cell: Cell): T? = catalog[cell]

    override fun set(cell: Cell, value: T?) {
        catalog[cell] = value
    }

    override fun all(predicate: (T?) -> Boolean): Boolean = catalog.values.all(predicate)

    override fun any(predicate: (T?) -> Boolean): Boolean = catalog.values.any(predicate)

    override fun find(predicate: (T?) -> Boolean): Cell? = catalog
        .entries
        .find { entry -> predicate(entry.value) }
        ?.key

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> = catalog
        .entries
        .filter { entry -> predicate(entry.value) }
        .map { it.key }

}