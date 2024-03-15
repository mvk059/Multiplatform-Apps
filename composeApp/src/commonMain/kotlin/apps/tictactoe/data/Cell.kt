package apps.tictactoe.data

data class Cell(
  val row: Int,
  val column: Int,
  var symbol: Symbol? = null
) {

  val isOccupied: Boolean
    get() = symbol != null

  fun occupy(symbol: Symbol) {
    if (isOccupied) {
      throw IllegalStateException("Cell [$row,$column] is already occupied.")
    }
    this.symbol = symbol
  }

  fun clear() {
    symbol = null
  }
}
