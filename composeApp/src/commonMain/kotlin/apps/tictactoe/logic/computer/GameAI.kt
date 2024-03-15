package apps.tictactoe.logic.computer

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

interface GameAI {
  fun calculateMove(board: Board, symbol: Symbol): Cell
}