package exercise5

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard = GameBoardImpl(width)

// TODO Implement SquareBoard Interface
class SquareBoardImpl(size: Int): SquareBoard {
    private val board = List(size) { i -> List(size) {
        j -> Cell(i+1, j+1)
    } }

    override val width: Int
        get() = board.size

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        board.forEach { if (Cell(i, j) in it) return board[i][j] }
        return null
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (getCellOrNull(i, j) != null) return board[i][j]
        throw IllegalArgumentException()
    }

    override fun getAllCells(): Collection<Cell> {
        return board.flatten()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return board.forEach {  }
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }

}

fun main() {
    val sboard = createSquareBoard(6)
    sboard.getRow(1, 5..2).forEach { println(it) }
}

// TODO extend SquareBoardImpl and implement GameBoard interface
class GameBoardImpl(size: Int): GameBoard {
    override fun get(cell: Cell): String? {
        TODO("Not yet implemented")
    }

    override fun set(cell: Cell, value: String?) {
        TODO("Not yet implemented")
    }

    override fun filter(predicate: (String?) -> Boolean): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun find(predicate: (String?) -> Boolean): Cell? {
        TODO("Not yet implemented")
    }

    override fun any(predicate: (String?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (String?) -> Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override val width: Int
        get() = TODO("Not yet implemented")

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("Not yet implemented")
    }

    override fun getCell(i: Int, j: Int): Cell {
        TODO("Not yet implemented")
    }

    override fun getAllCells(): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }
}