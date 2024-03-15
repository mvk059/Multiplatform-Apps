package apps.tictactoe.logic

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.Player
import apps.tictactoe.logic.winconditions.WinCondition

interface GameManager {

  fun initializeGame(players: List<Player>, boardSize: Int, winConditions: List<WinCondition>)
  fun startGame()
  fun makeMove(player: Player, cell: Cell)
  fun undoMove()
  fun resetGame()
  fun onCellClicked(row: Int, col: Int)
  fun getBoardState(): Array<Array<Cell>>
}