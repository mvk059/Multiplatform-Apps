package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

interface WinConditions {
    fun checkWin(board: Board, lastMove: Cell): Boolean
}
