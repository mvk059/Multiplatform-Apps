package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

interface WinCondition {
    fun checkWin(board: Board, symbol: Symbol, lastMove: Cell): Boolean
}
