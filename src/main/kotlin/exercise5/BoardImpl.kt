package exercise5

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)

fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

open class SquareBoardImpl(private val size: Int): SquareBoard {
    final override val width: Int
        get() = size

    private val matrix: Array<Array<Cell>> = Array(width) { i ->
        Array(width) { j ->
            Cell(i+1,j+1)
        }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if(i in 0..width && j in 0..width) return matrix[i][j]
        return null
    }

    override fun getCell(i: Int, j: Int): Cell {
        require(i in 0..width && j in 0..width)
        return matrix[i][j]
    }

    override fun getAllCells(): Collection<Cell> {
        val result = mutableListOf<Cell>()
        matrix.forEach { it.forEach { cell -> result.add(cell) } }
        return result
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val row = getAllCells().filter { cell -> cell.i == i }
        val result = row.filter { cell -> cell.j in jRange }
        if(jRange.first > jRange.last) return result.reversed()
        return result
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val column = getAllCells().filter { cell -> cell.j == j }
        val result = column.filter { cell -> cell.i in iRange }
        if(iRange.first > iRange.last) return result.reversed()
        return result
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction){
            Direction.UP -> getCellOrNull(i-1, j)
            Direction.DOWN -> getCellOrNull(i+1, j)
            Direction.LEFT -> getCellOrNull(i, j-1)
            Direction.RIGHT -> getCellOrNull(i, j+1)
        }
    }
}

class GameBoardImpl<T>(size: Int): GameBoard<T>, SquareBoardImpl(size) {

    private val matrix: MutableMap<Cell, T> = mutableMapOf()

    override fun get(cell: Cell): T? {
        return matrix[cell]
    }

    override fun set(cell: Cell, value: T?) {
       if(value != null) matrix[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        val result: MutableList<Cell> = mutableListOf()
        matrix.forEach { if(predicate(it.value)) result.add(it.key) }
        return result
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        matrix.forEach { if(predicate(it.value)) return it.key }
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        matrix.forEach { if(predicate(it.value)) return true }
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        matrix.forEach { if(!predicate(it.value)) return false }
        return true
    }

}