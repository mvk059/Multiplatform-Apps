package apps.tictactoe.logic.manager

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.enums.GameWinStatus
import apps.tictactoe.data.enums.WinCondition
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.winconditions.*

class TicTacToeGameManager : GameManager {

  override fun makeMove(player: PlayerConfig, board: Board, cell: Cell): Boolean {
    if (cell.isOccupied) return false
    return board.placeSymbol(cell, player.symbol)
  }

  override fun checkForWin(board: Board, cell: Cell, winConditions: Set<WinCondition>): GameWinStatus {
    return if (winConditions.toWinConditions().any { it.checkWin(board, cell) }) return GameWinStatus.WIN
    else if (board.isFull()) GameWinStatus.DRAW
    else GameWinStatus.IN_PROGRESS
  }

  override fun undoMove(board: Board, moveHistory: MutableList<Pair<PlayerConfig, Cell>>) {
    if (moveHistory.isEmpty()) return

    val (player, cell) = moveHistory.removeAt(moveHistory.lastIndex)
    board.clearCell(cell)
    println("Move undone. It's ${player.name}'s turn again.")
  }

  override fun resetGame() {
  }

  override fun onCellClicked(row: Int, col: Int) {
  }

  private fun Set<WinCondition>.toWinConditions(): List<WinConditions> {
    return this.map {
      when (it) {
        WinCondition.HORIZONTAL -> HorizontalWin()
        WinCondition.VERTICAL -> VerticalWin()
        WinCondition.DIAGONAL -> DiagonalWin()
        WinCondition.CORNER -> CornerWin()
      }
    }
  }
}
