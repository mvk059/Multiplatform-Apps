package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

class HorizontalWin : WinConditions {
    override fun checkWin(board: Board, lastMove: Cell): Boolean {
        return board.board[lastMove.row].all { cell -> cell.symbol == lastMove.symbol }
    }
}
