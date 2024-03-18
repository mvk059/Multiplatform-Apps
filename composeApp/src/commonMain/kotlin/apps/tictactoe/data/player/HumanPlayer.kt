package apps.tictactoe.data.player

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

class HumanPlayer(playerConfig: PlayerConfig) : Player(playerConfig) {

  override fun makeMove(board: Board, cell: Cell) {
    if (!board.placeSymbol(cell, playerConfig.symbol)) {
      throw IllegalArgumentException("Cell at [${cell.row}, ${cell.column}] is already occupied or invalid.")
    }
  }
}