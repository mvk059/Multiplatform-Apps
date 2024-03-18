package apps.tictactoe.data.player

import apps.tictactoe.data.Board
import apps.tictactoe.data.Cell
import apps.tictactoe.data.Symbol
import apps.tictactoe.logic.computer.GameAI

class ComputerPlayer(playerConfig: PlayerConfig, private val gameAI: GameAI) : Player(playerConfig) {

  override fun makeMove(board: Board, cell: Cell) {
    val chosenCell = gameAI.calculateMove(board, playerConfig.symbol)
    if (!board.placeSymbol(chosenCell, playerConfig.symbol)) {
      throw IllegalStateException("AI selected an invalid or already occupied cell at [${chosenCell.row}, ${chosenCell.column}].")
    }
  }
}