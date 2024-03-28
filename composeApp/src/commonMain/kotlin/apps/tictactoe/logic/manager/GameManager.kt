package apps.tictactoe.logic.manager

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.enums.GameWinStatus
import apps.tictactoe.data.enums.WinCondition
import apps.tictactoe.data.player.PlayerConfig

interface GameManager {

  fun makeMove(player: PlayerConfig, board: Board, cell: Cell): Boolean

  fun checkForWin(board: Board, cell: Cell, winConditions: Set<WinCondition>): GameWinStatus

  fun resetGame()

  fun onCellClicked(row: Int, col: Int)
}