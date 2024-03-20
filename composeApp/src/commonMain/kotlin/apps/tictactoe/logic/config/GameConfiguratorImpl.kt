package apps.tictactoe.logic.config

import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig

class GameConfiguratorImpl : GameConfigurator {

  override fun initializeForHumanPlayers(numberOfPlayers: Int): List<PlayerConfig> {
    val players = mutableListOf<PlayerConfig>()
    val symbols = Symbol.getAllSymbols()
    val names = generateRandomNames()

    for (i in 0 until numberOfPlayers) {
      players.add(PlayerConfig(name = names[i], symbol = symbols[i]))
    }

    return players
  }

  override fun updatePlayers(numberOfPlayers: Int, playersInfo: List<PlayerConfig>): List<PlayerConfig> {
    val players = mutableListOf<PlayerConfig>()
    val currentSize = playersInfo.size

    if (numberOfPlayers < currentSize) {
      for (i in 0 until numberOfPlayers) {
        players.add(playersInfo[i])
      }
      return players
    }

    players.addAll(playersInfo)
    val selNames = playersInfo.map { it.name }
    val selSymbol = playersInfo.map { it.symbol }
    val names = generateRandomNames().filter { it !in selNames }
    val symbols = Symbol.getAllSymbols().filter { it !in selSymbol }

    var j = 0
    for (i in currentSize until numberOfPlayers) {
      players.add(PlayerConfig(names[j], symbols[j]))
      j = j.inc()
    }

    println("UpdatePlayers: ${playersInfo.joinToString()}\n${names.joinToString()}")


    return players
  }

  override fun updatePlayerConfig(
    index: Int,
    playerConfig: PlayerConfig,
    playersInfo: List<PlayerConfig>
  ): List<PlayerConfig> {
    val players = mutableListOf<PlayerConfig>()
    players.addAll(playersInfo)
    players[index] = playerConfig
    return players
  }

  private fun generateRandomNames(): List<String> {
    val names = listOf("Alice", "Bob", "Charlie", "Dave", "Eve"/*, "Mike", "Jack"*/) // Add more as needed
    return names//.shuffled()
  }

}