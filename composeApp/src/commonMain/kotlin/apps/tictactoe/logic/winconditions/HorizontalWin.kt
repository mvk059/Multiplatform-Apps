package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

class HorizontalWin : WinCondition {
    override fun checkWin(board: Board, symbol: Symbol, lastMove: Cell): Boolean {
        return board.board[lastMove.row].all { cell -> cell.symbol == symbol }
    }
}
