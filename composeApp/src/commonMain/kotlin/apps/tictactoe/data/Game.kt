package apps.tictactoe.data

import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.computer.GameAI
import apps.tictactoe.logic.enums.WinCondition

data class Game(
  val players: List<PlayerConfig>,
  val vsComputer: Boolean,
  val aiDifficulty: GameAI?,
  val numberOfPlayers: Int,
  val currentPlayerIndex: Int,
  val winConditions: Set<WinCondition>,
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
      winConditions = setOf(WinCondition.HORIZONTAL, WinCondition.VERTICAL, WinCondition.DIAGONAL)
    )
  }
}
