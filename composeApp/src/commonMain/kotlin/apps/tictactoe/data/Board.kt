package apps.tictactoe.data

data class Board(val size: Int) {

  var board: Array<Array<Cell>>
    private set

  init {
    board = Array(size) { rowIndex ->
      Array(size) { columnIndex ->
        Cell(rowIndex, columnIndex)
      }
    }
  }

  fun placeSymbol(cell: Cell, symbol: Symbol): Boolean {
    if (cell.isOccupied) {
      return false
    }
    cell.occupy(symbol)
    return true
  }

  fun clearCell(cell: Cell) {

  }

  fun resetBoard() {
    for (row in board.indices) {
      for (col in board[row].indices) {
        board[row][col].clear()
      }
    }
  }

  // TODO Can be improved
  fun isFull(): Boolean {
    return board.all { row -> row.all { cell -> cell.isOccupied } }
  }

}