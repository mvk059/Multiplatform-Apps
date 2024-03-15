package apps.tictactoe.data.player

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

abstract class Player(val name: String, val symbol: Symbol) {

  abstract fun makeMove(board: Board, cell: Cell)
}