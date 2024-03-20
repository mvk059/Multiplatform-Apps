package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

class VerticalWin : WinConditions {
    override fun checkWin(board: Board, lastMove: Cell): Boolean {
        return board.board.indices.all { rowIndex -> board.board[rowIndex][lastMove.column].symbol == lastMove.symbol }
    }
}
