package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

class CornerWin : WinConditions {
    override fun checkWin(board: Board, lastMove: Cell): Boolean {
        val size = board.size
        return listOf(
            board.board[0][0].symbol,
            board.board[0][size - 1].symbol,
            board.board[size - 1][0].symbol,
            board.board[size - 1][size - 1].symbol
        ).all { occupant -> occupant == lastMove.symbol }
    }
}
