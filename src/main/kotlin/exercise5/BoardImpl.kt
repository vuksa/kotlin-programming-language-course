package exercise5

// TODO Instantiate SquareBoard
fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

// TODO Instantiate GameBoard
fun <T> createGameBoard(width: Int): GameBoard = GameBoardImpl(width)

// TODO Implement SquareBoard Interface
open class SquareBoardImpl(size: Int) : SquareBoard {
    protected val board = List(size) { x ->
        List(size) { y ->
            Cell(x + 1, y + 1)
        }
    }

    override val width = size

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if(i < 1 || i > width || j < 1 || j > width) return null

        return board[i - 1][j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        val cell = getCellOrNull(i, j)
        require(cell != null) {
            throw IllegalArgumentException("Wrong parameter!")
        }

        return cell
    }

    override fun getAllCells(): Collection<Cell> {
        return board.flatten()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.filter { it in 1..width }.
                      map { getCell(i, it) }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return iRange.filter { it in 1..width }.
                      map { getCell(it, j) }
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when(direction) {
            Direction.UP -> getCellOrNull(i - 1, j)
            Direction.DOWN -> getCellOrNull(i + 1, j)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
            Direction.LEFT -> getCellOrNull(i, j - 1)
        }
    }


}

class GameBoardImpl(size: Int) : SquareBoardImpl(size), GameBoard {

    private val map = mutableMapOf<Cell, String?>()

    override fun get(cell: Cell): String? {
        return map.get(cell)
    }

    init {
        board.flatten().forEach {
            map[it] = null
        }
    }

    override fun set(cell: Cell, value: String?) {
        map[cell] = value
    }

    override fun filter(predicate: (String?) -> Boolean): Collection<Cell> {
        return map.filter { predicate(it.value) }.keys
    }

    override fun find(predicate: (String?) -> Boolean): Cell? {
        val all = filter(predicate)
        return if (all.isEmpty()) null
        else all.first()
    }

    override fun any(predicate: (String?) -> Boolean): Boolean {
        return !filter(predicate).isEmpty()
    }

    override fun all(predicate: (String?) -> Boolean): Boolean {
        return filter(predicate).size == map.size
    }

}