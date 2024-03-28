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
    if (cell.isOccupied) return false
    cell.occupy(symbol)
    return true
  }

  fun clearCell(cell: Cell) {
    board[cell.row][cell.column].clear()
  }

  fun resetBoard() {
    for (row in board.indices) {
      for (col in board[row].indices) {
        board[row][col].clear()
      }
    }
  }

  fun isFull(): Boolean {
    return isBoardInState { cell -> cell.isOccupied }
  }

  fun isEmpty(): Boolean {
    return isBoardInState { cell -> !cell.isOccupied }
  }

  private fun isBoardInState(condition: (Cell) -> Boolean): Boolean {
    return board.none { row -> row.any { cell -> !condition(cell) } }
  }

}