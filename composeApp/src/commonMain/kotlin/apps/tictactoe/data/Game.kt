package apps.tictactoe.data

import apps.tictactoe.data.enums.GameStatus
import apps.tictactoe.data.enums.WinCondition
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.computer.GameAI

data class Game(
  val players: List<PlayerConfig>,
  val vsComputer: Boolean,
  val aiDifficulty: GameAI?,
  val numberOfPlayers: Int,
  val currentPlayerIndex: Int,
  val gameStatus: GameStatus,
  val winConditions: Set<WinCondition>,
  val currentPlayer: PlayerConfig?,
  val gameBoard: Board,
  val moveHistory: List<Pair<PlayerConfig, Cell>>
) {

  companion object {

    const val MIN_PLAYERS = 2
    const val MAX_PLAYERS = 5

    fun init(): Game = Game(
      players = listOf(),
      vsComputer = false,
      aiDifficulty = null,
      numberOfPlayers = 2,
      currentPlayerIndex = 0,
      gameStatus = GameStatus.INTRO,
      winConditions = setOf(WinCondition.HORIZONTAL, WinCondition.VERTICAL, WinCondition.DIAGONAL),
      currentPlayer = null,
      gameBoard = Board(3),
      moveHistory = mutableListOf()
    )
  }
}
