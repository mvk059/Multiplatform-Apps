package apps.tictactoe.logic.manager

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.Player
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.winconditions.WinCondition

interface GameManager {

  fun initializeGame(players: List<PlayerConfig>, boardSize: Int, winConditions: List<WinCondition>)
  fun startGame()
  fun makeMove(player: PlayerConfig, cell: Cell)
  fun undoMove()
  fun resetGame()
  fun onCellClicked(row: Int, col: Int)
  fun getBoardState(): Array<Array<Cell>>
}