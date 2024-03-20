package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell

class DiagonalWin : WinConditions {
    override fun checkWin(board: Board, lastMove: Cell): Boolean {
        // Check primary diagonal
        val primaryDiagonalWin = board.board.indices.all { index ->
            board.board[index][index].symbol == lastMove.symbol
        }
        
        // Check secondary diagonal
        val secondaryDiagonalWin = board.board.indices.all { index ->
            board.board[index][board.size - 1 - index].symbol == lastMove.symbol
        }

        return primaryDiagonalWin || secondaryDiagonalWin
    }
}
