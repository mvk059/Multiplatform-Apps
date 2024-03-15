package apps.tictactoe.logic.computer

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol

class EasyAI : GameAI {
    override fun calculateMove(board: Board, symbol: Symbol): Cell {
        // Simplified example: select a random empty cell
        val emptyCells = board.board.flatten().filter { !it.isOccupied }
        if (emptyCells.isEmpty()) throw IllegalStateException("No available moves.")
        return emptyCells.random()
    }
}
