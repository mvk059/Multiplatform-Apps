package apps.tictactoe.data

import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.computer.GameAI

data class Game(
  val players: List<PlayerConfig>,
  val vsComputer: Boolean,
  val aiDifficulty: GameAI?,
  val numberOfPlayers: Int,
  val currentPlayerIndex: Int,
) {

  companion object {
    fun init(): Game = Game(
      players = listOf(),
      vsComputer = false,
      aiDifficulty = null,
      numberOfPlayers = 2,
      currentPlayerIndex = 0,
    )
  }
}
