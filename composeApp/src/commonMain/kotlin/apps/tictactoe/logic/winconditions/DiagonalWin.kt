package apps.tictactoe.logic.winconditions

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

class DiagonalWin : WinCondition {
    override fun checkWin(board: Board, symbol: Symbol, lastMove: Cell): Boolean {
        // Check primary diagonal
        val primaryDiagonalWin = board.board.indices.all { index ->
            board.board[index][index].symbol == symbol
        }
        
        // Check secondary diagonal
        val secondaryDiagonalWin = board.board.indices.all { index ->
            board.board[index][board.size - 1 - index].symbol == symbol
        }

        return primaryDiagonalWin || secondaryDiagonalWin
    }
}
