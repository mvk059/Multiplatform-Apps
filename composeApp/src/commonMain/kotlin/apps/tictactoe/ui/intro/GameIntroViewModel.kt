package apps.tictactoe.ui.intro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import apps.tictactoe.data.Game
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.config.GameConfigurator

class GameIntroViewModel(private val gameConfigurator: GameConfigurator) {

  // Holds the current game configuration
  var game: MutableState<Game> = mutableStateOf(Game.init())

  init {
    Symbol.initSymbols()
  }

  fun initializeForHumanPlayers(numberOfPlayers: Int) {
    val playersInfo = gameConfigurator.initializeForHumanPlayers(numberOfPlayers)
    game.value = game.value.copy(
      players = playersInfo,
      numberOfPlayers = numberOfPlayers,
      vsComputer = false,
      currentPlayerIndex = 0,
    )
  }

  fun updatePlayers(numberOfPlayers: Int) {
    val playersInfo = gameConfigurator.updatePlayers(numberOfPlayers, game.value.players)
    game.value = game.value.copy(
      players = playersInfo,
      numberOfPlayers = numberOfPlayers,
    )
  }

  fun updatePlayerConfig(index: Int, playerConfig: PlayerConfig) {
    val playersInfo = gameConfigurator.updatePlayerConfig(index, playerConfig, game.value.players)
    game.value = game.value.copy(
      players = playersInfo,
    )
  }

  fun getAvailableSymbols(): List<Symbol> {
    val usedSymbols = game.value.players.map { it.symbol }
    return Symbol.getAllSymbols().filter { it !in usedSymbols }
  }
}