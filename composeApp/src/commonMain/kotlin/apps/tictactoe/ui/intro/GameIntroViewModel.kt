package apps.tictactoe.ui.intro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import apps.tictactoe.data.Game
import apps.tictactoe.data.Symbol
import apps.tictactoe.data.player.PlayerConfig
import apps.tictactoe.logic.config.GameConfigurator
import apps.tictactoe.data.enums.WinCondition

class GameIntroViewModel(private val gameConfigurator: GameConfigurator) {

  // Holds the current game configuration
  var game: MutableState<Game> = mutableStateOf(Game.init())
    private set

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

  fun onWinConditionUpdate(condition: WinCondition) {
    val currentConditions = game.value.winConditions.toMutableSet()
    if (currentConditions.contains(condition)) currentConditions.remove(condition)
    else currentConditions.add(condition)

    game.value = game.value.copy(
      winConditions = currentConditions.toSet()
    )
  }

  fun getAvailableSymbols(): List<Symbol> {
    val usedSymbols = game.value.players.map { it.symbol }
    return Symbol.getAllSymbols().filter { it !in usedSymbols }
  }

  fun validateIntroSetup(): Boolean {
    val playerName = game.value.players.map { it.name.isNotBlank() }.isNotEmpty()
    val playerSymbol = game.value.players.map { it.symbol.value.isNotEmpty() }.isNotEmpty()
    val playerWinningConditions = game.value.winConditions.isNotEmpty()
    return playerName && playerSymbol && playerWinningConditions
  }
}