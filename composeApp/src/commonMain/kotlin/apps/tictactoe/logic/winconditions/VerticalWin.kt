package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

class VerticalWin : WinCondition {
    override fun checkWin(board: Board, symbol: Symbol, lastMove: Cell): Boolean {
        return board.board.indices.all { rowIndex -> board.board[rowIndex][lastMove.column].symbol == symbol }
    }
}
